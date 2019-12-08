package com.sbt.JDBCTest;

import java.sql.*;

/**
 * Created by SBT-Klyshov-DA on 04.07.2018.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        try ( Connection connection = DriverManager.getConnection("jdbc:h2:mem:test") ){

            Statement statement = connection.createStatement();
            statement.execute("create table user(" +
                    "id integer primary key auto_increment, " +
                    "name varchar(100));" +
                    "CREATE UNIQUE INDEX name_pk ON user ( name );");
            statement.execute("insert into user(name) values('borya'),('petya')");

            //connection.setAutoCommit(false);
            //Statement statement = connection.createStatement()
            try {
                statement.execute("insert into user(name) values('kesha')");
                statement.execute("insert into user(name) values('kesha')");
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
            }

            ResultSet rs = statement.executeQuery("select * from user");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
            }
        }
    }
}
