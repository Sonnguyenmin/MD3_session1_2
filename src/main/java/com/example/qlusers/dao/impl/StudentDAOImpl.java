package com.example.qlusers.dao.impl;

import com.example.qlusers.dao.StudentDAO;
import com.example.qlusers.entity.Students;
import com.example.qlusers.utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Students> findAll() {
       //1. Tạo kết nối
        Connection con = ConnectionDB.openConnection();
        List<Students> students = new ArrayList<>();

        try {
            //2. Thực thi truy vấn
            PreparedStatement ps = con.prepareStatement("select * from Students");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students s = new Students();
                s.setId(rs.getInt("id"));
                s.setFullName(rs.getString("fullName"));
                s.setEmail(rs.getString("email"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setStatus(rs.getBoolean("status"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            // 3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }
        return students;
    }

    @Override
    public Students findById(Integer id) {
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from students where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Students s = new Students();
                s.setId(rs.getInt("id"));
                s.setFullName(rs.getString("fullName"));
                s.setEmail(rs.getString("email"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setStatus(rs.getBoolean("status"));
                return s;
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }
    }

    @Override
    public boolean addStudent(Students student) {
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();

        try {
            PreparedStatement ps = con.prepareStatement("insert into students (fullname, email, address, phone, status) values (?, ?, ? ,?, ?)");
            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhone());
            ps.setBoolean(5, student.isStatus());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }

    }

    @Override
    public boolean updateStudent(Students student) {
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();

        try {
            PreparedStatement ps = con.prepareStatement("update students set fullname = ?, email = ?, address = ?, phone = ?, status = ? where id = ?");
            ps.setString(1, student.getFullName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getAddress());
            ps.setString(4, student.getPhone());
            ps.setBoolean(5, student.isStatus());
            ps.setInt(6, student.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }

    }

    @Override
    public boolean deleteStudent(Integer id) {
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();

        try {
            PreparedStatement ps = con.prepareStatement("delete from students where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }
    }

    @Override
    public List<Students> findByName(String studentName) {
        List<Students> students = new ArrayList<>();
        //1. Mở kết nối đến database
        Connection con = ConnectionDB.openConnection();

        try {
            if (studentName == null || studentName.isEmpty()) {
                studentName = "%";
            }
            else {
                studentName = "%" + studentName + "%";
            }
            PreparedStatement ps = con.prepareStatement("select * from students where fullname like ?");
            ps.setString(1, "%" + studentName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Students s = new Students();
                s.setId(rs.getInt("id"));
                s.setFullName(rs.getString("fullName"));
                s.setEmail(rs.getString("email"));
                s.setAddress(rs.getString("address"));
                s.setPhone(rs.getString("phone"));
                s.setStatus(rs.getBoolean("status"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            //3. Đóng kết nối
            ConnectionDB.closeConnection(con);
        }
        return students;
    }
}
