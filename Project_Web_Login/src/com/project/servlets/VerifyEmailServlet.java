package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class VerifyEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu có khớp không
        if (!password.equals(confirmPassword)) {
            request.setAttribute("errorMessage", "Passwords do not match.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Tạo mã xác thực ngẫu nhiên
        String verificationCode = UUID.randomUUID().toString();

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Câu lệnh SQL để thêm người dùng
            String query = "INSERT INTO users (username, email, password, is_verified, verification_code) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setBoolean(4, false); // Mặc định chưa xác thực
            preparedStatement.setString(5, verificationCode);

            preparedStatement.executeUpdate();

            // Gửi email xác thực
            EmailSender.sendVerificationEmail(email, verificationCode);

            // Phản hồi thành công
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "<head>" +
                            "<title>Registration Successful</title>" +
                            "<style>" +
                            "body {" +
                            "  font-family: Arial, sans-serif;" +
                            "  display: flex;" +
                            "  justify-content: center;" +
                            "  align-items: center;" +
                            "  height: 100vh;" +
                            "  margin: 0;" +
                            "  background-color: #f0f8ff;" +
                            "}" +
                            ".notification {" +
                            "  text-align: center;" +
                            "  background-color: #4caf50;" +
                            "  color: white;" +
                            "  padding: 20px;" +
                            "  border-radius: 10px;" +
                            "  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);" +
                            "  animation: fadeIn 1s;" +
                            "}" +
                            "@keyframes fadeIn {" +
                            "  from { opacity: 0; transform: scale(0.8); }" +
                            "  to { opacity: 1; transform: scale(1); }" +
                            "}" +
                            "</style>" +
                            "<script>" +
                            "setTimeout(function() {" +
                            "  window.location.href = 'index.jsp';" +
                            "}, 3000);" +
                            "</script>" +
                            "</head>" +
                            "<body>" +
                            "<div class='notification'>" +
                            "  <h1>Registration Successful!</h1>" +
                            "  <p>Please check your email to verify your account.</p>" +
                            "</div>" +
                            "</body>" +
                            "</html>"
            );
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
