package com.ohgriaffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection(){

        Connection con = null;
        Properties prop = new Properties();

        // properties 파일에 담긴 정보 가져오기
        try {
            prop.load(new FileReader(
                    "src/main/java/com/ohgriaffers/config/connection-infor.properties"));

            // key-value 로 원하는 값 찾아오기
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            // 사용할 드라이버 등록
            Class.forName(driver);

            // DriverManager를 통해 connection 객체 생성
            con = DriverManager.getConnection(url, prop);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

    // close 메서드 만들기
    public static void close(Connection con) {
        try {
            if (con != null && !con.isClosed()) {

                con.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 오버로딩
    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {

                stmt.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {

                rset.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
