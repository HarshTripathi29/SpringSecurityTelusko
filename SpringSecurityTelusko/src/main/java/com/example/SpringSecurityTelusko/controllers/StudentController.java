package com.example.SpringSecurityTelusko.controllers;

import com.example.SpringSecurityTelusko.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Navin", "java"),
            new Student(2, "Reddy", "Javascript")
    ));

    @GetMapping("students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("students")
    public String addStudent(@RequestBody Student student){
        students.add(student);
        return "addded";
    }
}
