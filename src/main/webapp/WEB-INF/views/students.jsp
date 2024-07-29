<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 7/29/2024
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container p-3">
    <h1 class="m-2"> Danh sách sản phẩm</h1>
    <div style="display: flex; justify-content: space-between; align-items: center; margin: 20px 0;">
        <a href="/student-controller?action=add" class="btn btn-primary">Thêm mới sinh viên</a>
        <form action="/student-controller" method="post">
            <b>Tên sản phẩm: </b>
            <input type="text" name="studentName"/>
            <input type="submit" name="action" value="search"/>
        </form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr style="text-align: center">
            <th scope="col">STT</th>
            <th scope="col">Tên sinh viên</th>
            <th scope="col">Email</th>
            <th scope="col">Địa chỉ</th>
            <th scope="col">Số điện thoại</th>
            <th scope="col">Trạng thái</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${students}" var = "s" varStatus="loop">
            <tr style="text-align: center">
                <td>${loop.count}</td>
                <td>${s.fullName}</td>
                <td>${s.email}</td>
                <td>${s.address}</td>
                <td>${s.phone}</td>
                <td>${s.status ? "Hoạt động" : "Không hoạt động"}</td>
                <td><a href="/student-controller?action=detail&id=${s.id}" class="btn btn-info ">Chi tiết</a></td>
                <td><a href="/student-controller?action=edit&id=${s.id}" class="btn btn-warning ">Sửa</a></td>
                <td><a href="/student-controller?action=delete&id=${s.id}" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không')">Xóa</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
