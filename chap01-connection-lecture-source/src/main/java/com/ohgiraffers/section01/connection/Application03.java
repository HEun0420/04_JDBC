package com.ohgiraffers.section01.connection;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {
    public static void main(String[] args) {

        // DB 접속을 위한 Connection 객체
        // 생성하기 위해 레퍼런스 변수로 선언
        // 데이터베이스 종류, 계정 정보

        Properties prop = new Properties();


        Connection con = null;

        // 사용할 드라이버 등록
        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/section01/connection/jbdc-config.properties"));

            System.out.println("prop = " + prop);

            String driver = prop.getProperty("driver");
            String url= prop.getProperty("url");
            String user = prop.getProperty("user");
            String password= prop.getProperty("password");


            Class.forName("com.mysql.cj.jdbc.Driver");

            // DeriverManger를 통해 Connection 객체 생성
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/employee_db",
                    "ohgiraffers","ohgiraffers");

            // 연결객체 생성되었는지 확인. ( 객체의 주소값이 반환되는 것 확인)
            System.out.println("con = " + con);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
