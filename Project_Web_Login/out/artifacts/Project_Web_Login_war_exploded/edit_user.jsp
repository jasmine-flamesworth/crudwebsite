<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
  <title>Edit User</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h1 class="text-center">Edit User</h1>
  <form action="update_user" method="post" class="mt-4">
    <input type="hidden" name="id" value="${param.id}">
    <div class="mb-3">
      <label for="username" class="form-label">Username</label>
      <input type="text" class="form-control" id="username" name="username" value="${param.username}" required>
    </div>
    <div class="mb-3">
      <label for="email" class="form-label">Email</label>
      <input type="email" class="form-control" id="email" name="email" value="${param.email}" required>
    </div>
    <div class="mb-3">
      <label for="role" class="form-label">Role</label>
      <select class="form-control" id="role" name="role">
        <option value="user" ${param.role == 'user' ? 'selected' : ''}>User</option>
        <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>Admin</option>
      </select>
    </div>
    <button type="submit" class="btn btn-success">Update</button>
    <a href="account_management.jsp" class="btn btn-secondary">Cancel</a>
  </form>
</div>
</body>
</html>
