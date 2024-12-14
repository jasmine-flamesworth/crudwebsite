<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background: linear-gradient(135deg, #6a11cb, #2575fc);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0;
            font-family: 'Arial', sans-serif;
            color: #fff;
        }
        .card {
            background: #fff;
            color: #333;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
        }
        .card-header {
            background: #2575fc;
            color: #fff;
            font-size: 1.5rem;
            font-weight: bold;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }
        .btn-primary {
            background: #2575fc;
            border: none;
        }
        .btn-primary:hover {
            background: #6a11cb;
        }
        .text-decoration-none {
            color: #2575fc;
        }
        .text-decoration-none:hover {
            text-decoration: underline;
            color: #6a11cb;
        }
    </style>
</head>
<body>
<div class="card">
    <div class="card-header text-center">
        Login
    </div>
    <div class="card-body">
        <form action="login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" id="username" name="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" id="password" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>
        <div class="mt-3 text-center">
            <a href="register.jsp" class="text-decoration-none">Don't have an account? Register</a>
        </div>
        <div class="mt-3 text-center">
            <a href="forgot_password.jsp" class="text-decoration-none">Forgot password</a>
        </div>
    </div>
</div>
</body>
</html>
