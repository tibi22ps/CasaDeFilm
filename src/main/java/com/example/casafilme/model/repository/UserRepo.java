package com.example.casafilme.model.repository;


import com.example.casafilme.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private Connection connection;

    public UserRepo(Connection connection) {
        this.connection = connection;
    }

    public boolean addUser(User user) {
        try {
            String query = "INSERT INTO user (username, password, tip) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getTip().name());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                String userTipString = resultSet.getString("tip");

                User.Tip userTip = User.Tip.valueOf(userTipString);
                user = new User(userUsername, userPassword, userTip);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean updateUser(User user) {
        try {
            String query = "UPDATE user SET password = ?, tip = ? WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getTip().name());
            statement.setString(3, user.getUsername());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String username) {
        try {
            String query = "DELETE FROM user WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                String userTipString = resultSet.getString("tip");

                User.Tip userTip = User.Tip.valueOf(userTipString);
                User user = new User(userUsername, userPassword, userTip);
                users.add(user);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> filterUsersByTip(String tip) {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM user WHERE tip = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tip);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                String userTipString = resultSet.getString("tip");

                User.Tip userTip = User.Tip.valueOf(userTipString);
                User user = new User(userUsername, userPassword, userTip);
                users.add(user);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
