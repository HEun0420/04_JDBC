package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application05 {
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

    // 리스트 생성
        List<EmployeeDTO> empList = null;

    // employee dto 생성
        EmployeeDTO row = null;



    try {// 4. 연결객체의 createStatement()를 이용한 Statement 객체 생성
        stmt = con.createStatement();

        // employee 테이블 전체 조회
        String query = "select * from employee";

        // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet에 반환 받기
        rset = stmt.executeQuery(query);

        empList = new ArrayList<>();

        // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용
        EmployeeDTO selectedEmp = null;
        while (rset.next()) {

            row = new EmployeeDTO();

            row.setEmpID(rset.getString("EMP_Id"));
            row.setEmpName(rset.getString("EMP_NAME"));
            row.setEmpNo(rset.getNString("EMP_NO"));
            row.setEmail(rset.getString("EMAIL"));
            row.setPhone(rset.getString("PHONE"));
            row.setDeptCode(rset.getString("DEPT_CODE"));
            row.setJobCode(rset.getString("JOB_CODE"));
            row.setSalLevel(rset.getString("SAL_LEVEL"));
            row.setSalary(rset.getDouble("SALARY"));
            row.setBonus(rset.getDouble("BONUS"));
            row.setManagerId(rset.getString("MANAGER_ID"));
            row.setHireDate(rset.getDate("HIRE_DATE"));
            row.setEntDate(rset.getDate("ENT_DATE"));
            row.setEntYn(rset.getString("ENT_YN"));

            empList.add(row);
        }
        System.out.println(row.toString());
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
        // 오버로딩으로 통일시킴
        close(rset);
        close(stmt);
        close(con);

        for(EmployeeDTO emp: empList) {
            System.out.println(emp);
        }
    }


}
}
