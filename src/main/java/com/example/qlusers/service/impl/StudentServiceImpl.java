package com.example.qlusers.service.impl;

import com.example.qlusers.dao.StudentDAO;
import com.example.qlusers.dao.impl.StudentDAOImpl;
import com.example.qlusers.entity.Students;
import com.example.qlusers.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Students> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Students findById(Integer id) {
        return studentDAO.findById(id);
    }

    @Override
    public boolean addStudent(Students student) {
        return studentDAO.addStudent(student);
    }

    @Override
    public boolean updateStudent(Students student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(Integer id) {
        return studentDAO.deleteStudent(id);
    }

    @Override
    public List<Students> findByName(String studentName) {
        return studentDAO.findByName(studentName);
    }
}
