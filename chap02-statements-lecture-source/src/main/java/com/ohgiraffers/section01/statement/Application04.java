package com.ohgiraffers.section01.statement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {
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

        String query = "select * from employee where emp_id = '" + empId + "'";

        // 5. executeQuery()로 쿼리문을 실행하고 결과를 ResultSet에 반환 받기
        rset = stmt.executeQuery(query);


        // 6. 쿼리문의 결과를 컬럼 이름을 이용해서 사용
        EmployeeDTO selectedEmp = null;
        if (rset.next()) {

            selectedEmp = new EmployeeDTO();

            selectedEmp.setEmpID(rset.getString("EMP_Id"));
            selectedEmp.setEmpName(rset.getString("EMP_NAME"));
            selectedEmp.setEmpNo(rset.getNString("EMP_NO"));
            selectedEmp.setEmail(rset.getString("EMAIL"));
            selectedEmp.setPhone(rset.getString("PHONE"));
            selectedEmp.setDeptCode(rset.getString("DEPT_CODE"));
            selectedEmp.setJobCode(rset.getString("JOB_CODE"));
            selectedEmp.setSalLevel(rset.getString("SAL_LEVEL"));
            selectedEmp.setSalary(rset.getDouble("SALARY"));
            selectedEmp.setBonus(rset.getDouble("BONUS"));
            selectedEmp.setManagerId(rset.getString("MANAGER_ID"));
            selectedEmp.setHireDate(rset.getDate("HIRE_DATE"));
            selectedEmp.setEntDate(rset.getDate("ENT_DATE"));
            selectedEmp.setEntYn(rset.getString("ENT_YN"));

        }
        System.out.println(selectedEmp.toString());
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
