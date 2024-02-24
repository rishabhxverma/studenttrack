package ca.sfu.cmpt276.as2.studenttrack.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getAllStudents(Model model){
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "student/showAll";
    }

    @PostMapping("/student/add")
    public String addUser(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
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
}
