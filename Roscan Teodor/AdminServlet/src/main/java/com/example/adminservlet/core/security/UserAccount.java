package com.example.adminservlet.core.security;

import com.example.adminservlet.core.extra.Utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccount {

    public boolean isUserLoggedIn(HttpServletRequest request) {
        /* FOR JDBC Realm
        String loggedInUser = request.getRemoteUser();
        return loggedInUser != null && !loggedInUser.isEmpty();
        */

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedInUser") != null) {
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public String updateCredentials(HttpServletRequest request, String newUsername, String newPassword, String confirmPassword) {
        String response = "";

        // Get the current username from the session
        HttpSession session = request.getSession(false);
        String currentUsername = (String) session.getAttribute("loggedInUser");

        // Validate password confirmation
        if (!newPassword.equals(confirmPassword)) {
            return "failure";
        }

        // Encrypt the new password
        Utilities utilities = new Utilities();
        String encryptedPassword = utilities.encryptorSHA256(newPassword);

        // SQL for dropping and adding constraints
        String dropConstraintSQL = "ALTER TABLE roles DROP CONSTRAINT IF EXISTS roles_username_fkey";
        String addConstraintSQL = "ALTER TABLE roles ADD CONSTRAINT roles_username_fkey FOREIGN KEY (username) REFERENCES users(username) ON DELETE CASCADE";

        try (Connection connection = com.example.util.DatabaseConnection.getConnection();
             PreparedStatement dropConstraintStmt = connection.prepareStatement(dropConstraintSQL);
             PreparedStatement addConstraintStmt = connection.prepareStatement(addConstraintSQL);
             PreparedStatement updateRolesStmt = connection.prepareStatement(
                     "UPDATE roles SET username = ? WHERE username = ?");
             PreparedStatement updateUserStmt = connection.prepareStatement(
                     "UPDATE users SET username = ?, password = ? WHERE username = ?")) {

            // Start transaction
            connection.setAutoCommit(false);

            // Step 1: Drop the foreign key constraint temporarily
            dropConstraintStmt.executeUpdate();

            // Step 2: Update roles table first
            updateRolesStmt.setString(1, newUsername);
            updateRolesStmt.setString(2, currentUsername);
            int rolesUpdateCount = updateRolesStmt.executeUpdate();

            // Step 3: Update users table
            updateUserStmt.setString(1, newUsername);
            updateUserStmt.setString(2, encryptedPassword);
            updateUserStmt.setString(3, currentUsername);
            int userUpdateCount = updateUserStmt.executeUpdate();

            // Step 4: Add the foreign key constraint back
            addConstraintStmt.executeUpdate();

            // Commit if both updates were successful
            if (rolesUpdateCount > 0 && userUpdateCount > 0) {
                connection.commit();
                session.setAttribute("loggedInUser", newUsername);
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

    public String login(HttpServletRequest request, String username, String password) {
        Utilities utilities = new Utilities();
        try (Connection connection = com.example.util.DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next())
                    return "failure";

                String storedHashedPassword = rs.getString("password");
                String encryptedPassword = utilities.encryptorSHA256(password);

                if (encryptedPassword.equals(storedHashedPassword)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedInUser", username);
                    return "success";
                }
                else
                    return "failure";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error");
        }
        return "failure";
    }
}
