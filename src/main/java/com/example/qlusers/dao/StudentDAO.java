package com.example.qlusers.dao;

import com.example.qlusers.entity.Students;

import java.util.List;

public interface StudentDAO {
    List<Students> findAll();
    Students findById(Integer id);
    boolean addStudent(Students student);
    boolean updateStudent(Students student);
    boolean deleteStudent(Integer id);
    List<Students> findByName(String studentName);

}
