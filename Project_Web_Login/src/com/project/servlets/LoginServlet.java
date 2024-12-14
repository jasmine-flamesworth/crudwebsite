package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Truy vấn kiểm tra username, password và role
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Đăng nhập thành công
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", resultSet.getString("role")); // Lưu vai trò của người dùng

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().println(
                        "<!DOCTYPE html>" +
                                "<html>" +
                                "<head>" +
                                "<title>Login Successful</title>" +
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
                                "  window.location.href = 'home.jsp';" +
                                "}, 1500);" +
                                "</script>" +
                                "</head>" +
                                "<body>" +
                                "<div class='notification'>" +
                                "  <h1>Login Successful!</h1>" +
                                "  <p>You will be redirected to the home page in a moment...</p>" +
                                "</div>" +
                                "</body>" +
                                "</html>"
                );
            } else {
                // Sai username hoặc password
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}
