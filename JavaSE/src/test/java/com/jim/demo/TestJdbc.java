package com.jim.demo;

import com.mysql.jdbc.Driver;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.*;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/9/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestJdbc {
    public static final Logger log = Logger.getLogger(TestJdbc.class);
    private String url = "jdbc:mysql://127.0.0.1:3306/test";
    private String user = "root";
    private String password = "root";

    @Test
    public void testJdbc() {
        try {
            Driver mysqlDriver = new Driver();
            DriverManager.registerDriver(mysqlDriver);
            Connection connection = DriverManager.getConnection(url, user, password);
            log.info("connection = " + connection);
            String sql = "insert into geocoder(id,createOn,requestOfTimes) values(?,?,?)";
            log.info("sql = " + sql);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 3);
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setInt(3, new Random().nextInt(1000));
            int rows = preparedStatement.executeUpdate();
            log.info("rows = " + rows);

            sql = "select * from geocoder";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt(1);
                Date createOn = resultSet.getDate(2);
                int requestOfTimes = resultSet.getInt(3);
                System.out.println("id = " + id);
                System.out.println("createOn = " + createOn);
                System.out.println("requestOfTimes = " + requestOfTimes);
            }

            connection.close();
        } catch (SQLException e) {
            log.error("test jdbc", e);
        }
    }
}
