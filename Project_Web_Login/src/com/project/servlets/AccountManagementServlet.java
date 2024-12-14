package com.project.servlets;

import com.project.utils.DatabaseConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManagementServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra vai trò người dùng
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            // Người dùng không phải admin
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println(
                    "<!DOCTYPE html>" +
                            "<html>" +
                            "<head><title>Access Denied</title></head>" +
                            "<body>" +
                            "<h1>Access Denied</h1>" +
                            "<p>Bạn không có quyền để truy cập vào trang này.</p>" +
                            "<a href='home.jsp'>Quay lại trang chủ</a>" +
                            "</body>" +
                            "</html>"
            );
            return;
        }

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Truy vấn danh sách tài khoản
            String query = "SELECT idusers, username, email, password, role FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<HashMap<String, String>> usersList = new ArrayList<>();
            while (resultSet.next()) {
                HashMap<String, String> user = new HashMap<>();
                user.put("id", resultSet.getString("idusers"));
                user.put("username", resultSet.getString("username"));
                user.put("email", resultSet.getString("email"));
                user.put("password", resultSet.getString("password"));
                user.put("role", resultSet.getString("role"));
                usersList.add(user);
            }

            // Ghi log số lượng tài khoản truy vấn được
            System.out.println("Users count: " + usersList.size());

            // Gửi danh sách tài khoản tới JSP
            request.setAttribute("usersList", usersList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("account_management.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
