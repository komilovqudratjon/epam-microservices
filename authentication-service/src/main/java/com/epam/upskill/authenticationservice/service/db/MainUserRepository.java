package com.epam.upskill.authenticationservice.service.db;

import com.epam.upskill.authenticationservice.model.RoleName;
import com.epam.upskill.authenticationservice.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * @description: TODO
 * @date: 02 February 2024 $
 * @time: 5:55 PM 43 $
 * @author: Qudratjon Komilov
 */
@Service
@AllArgsConstructor
public class MainUserRepository {

    public final DatabaseConnection databaseConnection;

    public Optional<Users> findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Users users = mapResultSetToUsers(rs);
                return Optional.of(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void changePassword(String username, String newPassword) {
        String query = "UPDATE users SET password = ? WHERE username = ?";
        try (Connection conn = databaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Users mapResultSetToUsers(ResultSet rs) throws SQLException {
        Users build = Users.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .isActive(rs.getBoolean("is_active"))
                .dateOfBirth(rs.getDate("date_of_birth"))
                .address(rs.getString("address"))
                .role(RoleName.valueOf(rs.getString("role")))
                .blockedEndDate(rs.getDate("blocked_end_date"))
                .count(rs.getInt("count"))
                .accountNonExpired(rs.getBoolean("account_non_expired"))
                .accountNonLocked(rs.getBoolean("account_non_locked"))
                .credentialsNonExpired(rs.getBoolean("credentials_non_expired"))
                .enabled(rs.getBoolean("enabled"))
                .build();
        build.setCreatedAt(rs.getTimestamp("created_at"));
        build.setUpdatedAt(rs.getTimestamp("updated_at"));
        return build;

    }
}
