package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    Connection connection;

    public void open() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.132:5432/student", "student", "computer");
            System.out.println("connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
            System.out.println("disconnect");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> select(String predicate) {
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * From student WHERE " + predicate)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Student st = new Student(id, name);
                students.add(st);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void insert(int id, String name) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO student(id,name) VALUES (" + id + ",'" + name + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String oldData, String newData) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE student SET " + newData + " WHERE " + oldData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String predicate) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM student WHERE " + predicate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

