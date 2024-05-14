package com.example.casafilme.model.repository;

import com.example.casafilme.model.User;

import java.sql.*;



public class BazaDateFilme {
    public Connection connection=null;
    public BazaDateFilme(){


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            String url = "jdbc:mysql://localhost:3307/casafilm3";
            String username = "root";
            String password = "";

            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection working");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public BazaDateFilme(Connection connection) {
        this.connection = connection;
    }
    public boolean autentificareUser(String username, String password) {
        try {

            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            try {
//                if (this.connection != null) {
//                    this.connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return false;
    }

    public User getAuthenticatedUser(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        User utilizatorAutentificat = null;

        try {
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            connection = this.connection;

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String userUsername = resultSet.getString("username");
                String userPassword = resultSet.getString("password");
                String userTipString = resultSet.getString("tip");

                if (userTipString != null && !userTipString.isEmpty()) {
                    User.Tip userTip = User.Tip.valueOf(userTipString);
                    utilizatorAutentificat = new User(userUsername, userPassword, userTip);
                } else {
                    System.out.println("User tip is null or empty");
                }
            } else {
                System.out.println("User inexistent");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return utilizatorAutentificat;
    }

    public void showLoginResult(boolean success) {
        if (success) {
            System.out.println("Logged in!");
        } else {
            System.out.println("Login failed.");
        }
    }

    public Connection getConnection(){
        return connection;
    }

}
