<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý tài khoản</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center">Quản lý tài khoản</h1>
    <table class="table table-striped mt-4">
        <thead>
        <tr>
            <th>NHẬN DẠNG</th>
            <th>Tên người dùng</th>
            <th>E-mail</th>
            <th>Mật khẩu</th>
            <th>Vai trò</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.password}</td>
                <td>${user.role}</td>
                <td>
                    <a href="edit_user.jsp?id=${user.id}" class="btn btn-warning btn-sm">Biên tập</a>
                    <a href="DeleteAccountServlet?id=${user.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản này?');">Xóa bỏ</a>

                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <a href="home.jsp" class="btn btn-primary">Quay lại trang chủ</a>
</div>
</body>
</html>
