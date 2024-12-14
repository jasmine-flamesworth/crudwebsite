package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            try (Connection connection = DatabaseConnection.getConnection()) {
                // Xóa tài khoản dựa trên id
                String query = "DELETE FROM users WHERE idusers = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Account with ID " + id + " deleted successfully.");
                } else {
                    System.out.println("No account found with ID " + id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error: " + e.getMessage());
                return;
            }
        }
        // Quay lại trang quản lý tài khoản
        response.sendRedirect("AccountManagementServlet");
    }
}
