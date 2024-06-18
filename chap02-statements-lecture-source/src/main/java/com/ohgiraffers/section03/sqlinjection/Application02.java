package com.ohgiraffers.section03.sqlinjection;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // Employee ID와 이름을 입력받고 두개가 일치하는 Employee가 있는지 확인하는 기능

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("ID와 이름 두개가 일치하는 employee가 있는지 확인하는 기능입니다.");
        System.out.print("ID를 입력하세요 : ");
        String empId = sc.nextLine();
        System.out.print("회원 이름을 입력하세요 : ");
        String empName = sc.nextLine();

        String query = "select * from employee where emp_id = ? and emp_name = ?";

        // select * from employee where emp_id = '200' and emp_name = '선동일'
        // select * from employee where emp_id = '123124251' and emp_name = '' or 1=1 and emp_id = '204'

        try {

           pstmt = con.prepareStatement(query);

           pstmt.setString(1,empId);
           pstmt.setString(2,empName);

           rset = pstmt.executeQuery();

            if(rset.next()) {
                System.out.println(rset.getString("emp_name") + "님 환영합니다.");
            } else {
                System.out.println(" 회원 정보가 없습니다. ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
