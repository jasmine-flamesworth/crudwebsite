<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        // Hàm kiểm tra định dạng email Gmail
        function validateEmail() {
            const emailField = document.getElementById("email");
            const email = emailField.value;
            const gmailRegex = /^[a-zA-Z0-9._%+-]+@gmail\.com$/; // Chỉ chấp nhận Gmail

            if (!gmailRegex.test(email)) {
                const errorDiv = document.getElementById("emailError");
                errorDiv.innerHTML = "Please enter a valid Gmail address (e.g., example@gmail.com).";
                emailField.classList.add("is-invalid");
                emailField.focus();
                return false;
            }
            emailField.classList.remove("is-invalid");
            return true;
        }

        // Hàm kiểm tra toàn bộ form
        function validateForm() {
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                const passwordError = document.getElementById("passwordError");
                passwordError.innerHTML = "Passwords do not match!";
                return false;
            }

            return validateEmail();
        }
    </script>
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header text-center">
                    <h4>Register</h4>
                </div>
                <div class="card-body">
                    <form method="post" action="VerifyEmailServlet" onsubmit="return validateForm();">
                        <div class="mb-3">
                            <label for="username" class="form-label">Username:</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email:</label>
                            <input type="email" id="email" name="email" class="form-control" required>
                            <div id="emailError" class="invalid-feedback"></div>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password:</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirmPassword" class="form-label">Confirm Password:</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                            <div id="passwordError" class="text-danger mt-2"></div>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
