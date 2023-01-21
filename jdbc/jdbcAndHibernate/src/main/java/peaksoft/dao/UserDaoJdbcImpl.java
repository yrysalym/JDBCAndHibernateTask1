package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("CREATE TABLE users(id SERIAL PRIMARY KEY, " +
                    "first_name VARCHAR NOT NULL," +
                    "last_name VARCHAR NOT NULL, " +
                    "age SMALLINT )");
            if (i >= 1) {
                System.out.println("Very good!!");
            } else {
                System.out.println("not not!!");
            }
            statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {

        Connection connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            int dropTableUsers = statement.executeUpdate("DROP TABLE users");
            if (dropTableUsers == 0) {
                System.out.println("Drop table!");
            } else {
                System.out.println("Not not!");
            }
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users( first_name,last_name,age) " +
                    "VALUES(?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try{
            Connection connection = Util.getConnection();
            String sql = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                System.out.println("id" + "first_name" + " " + "last_name" + " " + "age");
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                userList.add(user);
                resultSet.close();
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return userList;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("TRUNCATE TABLE users RESTART IDENTITY");
            while (resultSet.next()) {
                System.out.println("Very good!");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}