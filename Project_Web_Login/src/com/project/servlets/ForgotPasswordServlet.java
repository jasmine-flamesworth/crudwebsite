package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Truy vấn để lấy mật khẩu dựa vào email
            String query = "SELECT password FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Nếu tìm thấy email, lấy mật khẩu
                String password = resultSet.getString("password");

                // Gửi mật khẩu qua email
                String subject = "Your Password Recovery";
                String content = "Hello,\n\nYour password is: " + password +
                        "\n\nPlease keep it safe.\n\nBest regards,\nYour Support Team";

                EmailSender.send(email, subject, content);

                // Hiển thị thông báo thành công
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println(
                        "<!DOCTYPE html>" +
                                "<html>" +
                                "<head><title>Password Sent</title></head>" +
                                "<body>" +
                                "<h1>Password Sent Successfully</h1>" +
                                "<p>Please check your email to retrieve your password.</p>" +
                                "<a href='index.jsp'>Back to Login</a>" +
                                "</body>" +
                                "</html>"
                );
            } else {
                // Email không tồn tại trong cơ sở dữ liệu
                request.setAttribute("errorMessage", "Email not found.");
                request.getRequestDispatcher("forgot_password.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
