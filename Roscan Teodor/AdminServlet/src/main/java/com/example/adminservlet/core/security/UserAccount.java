package com.example.adminservlet.core.security;

import com.example.adminservlet.core.extra.Utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserAccount {

    public boolean isUserLoggedIn(HttpServletRequest request) {
        String loggedInUser = request.getRemoteUser();
        return loggedInUser != null && !loggedInUser.isEmpty();
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public String updateCredentials(HttpServletRequest request, String newUsername, String newPassword, String confirmPassword) {
        String response = "";
        String currentUsername = request.getRemoteUser();
        System.out.println("Old username: " + currentUsername);
        Utilities utilities = new Utilities();

        if (!newPassword.equals(confirmPassword)) {
            return "failure";
        }

        String encryptedPassword = utilities.encryptorSHA256(newPassword);

        try (Connection connection = com.example.util.DatabaseConnection.getConnection();
             PreparedStatement updateUserStmt = connection.prepareStatement(
                     "UPDATE users SET username = ?, password = ? WHERE username = ?");
             PreparedStatement updateRolesStmt = connection.prepareStatement(
                     "UPDATE roles SET username = ? WHERE username = ?")) {

            connection.setAutoCommit(false);

            updateUserStmt.setString(1, newUsername);
            updateUserStmt.setString(2, encryptedPassword);
            updateUserStmt.setString(3, currentUsername);
            int userUpdateCount = updateUserStmt.executeUpdate();

            updateRolesStmt.setString(1, newUsername);
            updateRolesStmt.setString(2, currentUsername);
            int rolesUpdateCount = updateRolesStmt.executeUpdate();

            if (userUpdateCount > 0 && rolesUpdateCount > 0) {
                connection.commit();
                response = "success";
            } else {
                connection.rollback();
                response = "failure";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "failure";
        }

        return response;
    }

}
