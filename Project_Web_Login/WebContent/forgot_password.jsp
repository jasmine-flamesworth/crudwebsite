<!DOCTYPE html>
<html lang="en">
<head>
  <title>Forgot Password</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card shadow">
        <div class="card-header text-center">
          <h4>Forgot Password</h4>
        </div>
        <div class="card-body">
          <form action="ForgotPasswordServlet" method="post">
            <div class="mb-3">
              <label for="email" class="form-label">Enter your registered email:</label>
              <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Send Password</button>
          </form>
        </div>
        <div class="card-footer text-center">
          <a href="index.jsp">Back to Login</a>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
