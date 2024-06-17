package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

    // 커넥션을 리턴해주는 메소드
    public static Connection getConnection() {
        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("prop = " + prop);

        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");


        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // DeriverManger를 통해 Connection 객체 생성
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_db",
                    "ohgiraffers", "ohgiraffers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 연결객체 생성되었는지 확인. ( 객체의 주소값이 반환되는 것 확인)
        System.out.println("con = " + con);

        return con;
    }
    // 커넥션을 닫아주는 메소드
}

