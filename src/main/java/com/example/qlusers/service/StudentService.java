package com.example.qlusers.service;

import com.example.qlusers.entity.Students;

import java.util.List;

public interface StudentService {
    List<Students> findAll();
    Students findById(Integer id);
    boolean addStudent(Students student);
    boolean updateStudent(Students student);
    boolean deleteStudent(Integer id);
    List<Students> findByName(String studentName);

}
