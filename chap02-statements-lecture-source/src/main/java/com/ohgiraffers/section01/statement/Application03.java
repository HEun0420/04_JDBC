package com.ohgiraffers.section01.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

    // 연결객체
        // 1. 객체 생성
    Connection con = getConnection();


    // 뭐리문을 저장하고, 실행하는 기능을 하는 용도의 인터페이스
    // 2. statment 생성
        Statement stmt = null;

        // 3. resultset 생성
    // *select* 결과집합을 받아올 용도의 인터페이스
    ResultSet rset = null;

    try {// 4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
        stmt = con.createStatement();

        // 스캐너로 emp_id를 입력받아 조회하기
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하려는 사번을 입력하세요 : ");
        String empId = sc.nextLine();

        String query = "select emp_id, emp_name from employee where emp_id = '" + empId + "'";

        // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet에 반환 받기
        rset = stmt.executeQuery(query);


        // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용
        if (rset.next()) {

            System.out.println(rset.getString("EMP_ID") + ", " + rset.getString("EMP_NAME"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        // 오버로딩으로 통일시킴
        close(rset);
        close(stmt);
        close(con);
    }


}
}
