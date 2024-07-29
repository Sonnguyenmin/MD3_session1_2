package com.example.qlusers.controller;

import com.example.qlusers.entity.Students;
import com.example.qlusers.service.StudentService;
import com.example.qlusers.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "StudentController", value = "/student-controller")
public class StudentController extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "add":
                req.getRequestDispatcher("/WEB-INF/views/add_student.jsp").forward(req, resp);
                break;
            case "edit":
            {
                Integer id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("studentEdit", studentService.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/edit_student.jsp").forward(req, resp);
                break;
            }
            case "delete":
            {
                Integer id = Integer.parseInt(req.getParameter("id"));
                studentService.deleteStudent(id);
//                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
                resp.sendRedirect("/student-controller");
                break;
            }
            case "detail":
            {
                Integer id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("studentDetail", studentService.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/detail_student.jsp").forward(req, resp);
                break;
            }
            default:
                req.setAttribute("students", studentService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "add":
            {
                String fullName = req.getParameter("fullName");
                String email = req.getParameter("email");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                Boolean status = Boolean.valueOf(req.getParameter("status"));
                Students students = new Students(fullName, null, email, address, phone, status);
                studentService.addStudent(students);
                resp.sendRedirect("/student-controller");
                break;
            }
            case "edit":
                Integer id = Integer.parseInt(req.getParameter("id"));
                String fullName = req.getParameter("fullName");
                String email = req.getParameter("email");
                String address = req.getParameter("address");
                String phone = req.getParameter("phone");
                Boolean status = Boolean.valueOf(req.getParameter("status"));
                Students students = new Students(fullName, id, email, address, phone, status);
                studentService.updateStudent(students);
                resp.sendRedirect("/student-controller");
                break;
            case "search":
                String studentName = req.getParameter("studentName");
                List<Students> studentResults = studentService.findByName(studentName);
                req.setAttribute("students", studentResults);
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
                break;
            default:
                req.setAttribute("students", studentService.findAll());
                req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        }
    }
}
