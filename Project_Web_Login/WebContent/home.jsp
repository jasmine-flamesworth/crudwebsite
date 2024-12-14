<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <!-- Thêm kiểm tra vai trò admin -->
    <%-- Sử dụng JSTL để kiểm tra sessionScope.role --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:if test="${sessionScope.role == 'admin'}">
        <a href="AccountManagementServlet" class="btn btn-info" style="margin: 10px;">Manage Accounts</a>
    </c:if>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow">
                <div class="card-header text-center">
                    <h4>Welcome</h4>
                </div>
                <div class="card-body text-center">
                    <h5>Welcome, ${sessionScope.username}!</h5>
                    <p>Enjoy exploring our platform.</p>
                    <a href="index.jsp" class="btn btn-danger">Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
