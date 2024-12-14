package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Cập nhật thông tin tài khoản trong cơ sở dữ liệu
            String query = "UPDATE users SET username = ?, email = ?, role = ? WHERE idusers = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, role);
            preparedStatement.setString(4, id);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                // Thành công, chuyển hướng về trang quản lý tài khoản
                response.sendRedirect("AccountManagementServlet");
            } else {
                // Không tìm thấy tài khoản để cập nhật
                request.setAttribute("errorMessage", "Failed to update user. Please try again.");
                request.getRequestDispatcher("edit_user.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("edit_user.jsp").forward(request, response);
        }
    }
}
