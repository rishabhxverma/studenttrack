package ca.sfu.cmpt276.as2.studenttrack.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sfu.cmpt276.as2.studenttrack.models.Student;
import ca.sfu.cmpt276.as2.studenttrack.models.StudentRepo;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/student/view")
    public String getAllStudents(Model model) {
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/showAll";
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHairColor = newStudent.get("hairColor");
        Double newGPA = Double.parseDouble(newStudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColor, newGPA));
        response.setStatus(201);
        return "student/addedStudent";
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        Student student = studentRepo.findById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/student/update")
    public ResponseEntity<String> updateStudent(@RequestParam("studentId") int studentId,
            @RequestParam("name") String name,
            @RequestParam("weight") int weight,
            @RequestParam("height") int height,
            @RequestParam("hairColor") String hairColor,
            @RequestParam("gpa") double gpa) {
        // Find the student by id
        Student student = studentRepo.findById(studentId);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }

        // Update student information
        student.setName(name);
        student.setWeight(weight);
        student.setHeight(height);
        student.setHairColor(hairColor);
        student.setGpa(gpa);

        // Save the updated student
        studentRepo.save(student);

        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
        // Find the student by id
        Student student = studentRepo.findById(id);
        if (student != null) {
            // Delete the student
            studentRepo.delete(student);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }

}
