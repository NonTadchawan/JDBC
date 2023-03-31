package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private Connection connection;

    public void open() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.132:5432/student", "student", "computer");
        System.out.println("connected");
    }

    public void close() throws SQLException {
        connection.close();
        System.out.println("disconnect");
    }

    public void select(String predicate) {
        List<Student> students = new ArrayList<>();
        try {
            open();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * From student WHERE " + predicate);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Student st = new Student(id, name);
                students.add(st);
            }
            close();
            for (Student s : students) {
                System.out.println(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert(int id,String name){
        try {
            open();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO student(id,name) VALUES ("+id+",'"+name+"')");
            close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

