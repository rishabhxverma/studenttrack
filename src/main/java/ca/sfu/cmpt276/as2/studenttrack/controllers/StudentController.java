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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ca.sfu.cmpt276.as2.studenttrack.models.Student;
import ca.sfu.cmpt276.as2.studenttrack.models.StudentRepo;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;

    @GetMapping("/student/view")
    public String getAllStudents(Model model){
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/showAll";
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD user");
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight =  Integer.parseInt(newStudent.get("height"));
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
    public ResponseEntity<String> updateStudent(@RequestParam("id") int id, @RequestBody Map<String, String> updatedStudentInfo) {
        // Find the student by id
        Student student = studentRepo.findById(id);
        if (student == null) {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }

        // Update student information
        if (updatedStudentInfo.containsKey("name")) {
            student.setName(updatedStudentInfo.get("name"));
        }
        if (updatedStudentInfo.containsKey("weight")) {
            student.setWeight(Integer.parseInt(updatedStudentInfo.get("weight")));
        }
        if (updatedStudentInfo.containsKey("height")) {
            student.setHeight(Integer.parseInt(updatedStudentInfo.get("height")));
        }
        if (updatedStudentInfo.containsKey("hairColor")) {
            student.setHairColor(updatedStudentInfo.get("hairColor"));
        }
        if (updatedStudentInfo.containsKey("gpa")) {
            student.setGpa(Double.parseDouble(updatedStudentInfo.get("gpa")));
        }

        // Save the updated student
        studentRepo.save(student);

        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/student/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudentByID(@RequestParam int sid) {
        studentRepo.deleteAllById(sid);
    }
}
