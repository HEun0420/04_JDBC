package com.ohgiraffers.section02.preparedstatment;

import com.ohgiraffers.model.dto.DepartmentDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {

    public static void main(String[] args) {

        /*
         * Department 테이블의 부서코드(아이디)를 입력받아서
         * 부서코드(아이디), 부서명, 지역코드를
         * DepartmentDTO 객체에 담고,
         * DepartmentDTO를 출력하세요
         * */

        // Scanner를 사용한 PreparedStatement

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        // Scanner 부서코드 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 부서코드를 입력해주세요 : ");
        String deptId = sc.nextLine();

        String query = "select * from department where dept_id = ?";

        DepartmentDTO selectedDepartment = null;
        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, deptId);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedDepartment = new DepartmentDTO();

                selectedDepartment.setDeptID(rset.getString("dept_id"));
                selectedDepartment.setDeptTitle(rset.getString("dept_title"));
                selectedDepartment.setLocationId(rset.getString("location_id"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        System.out.println("selectedDepartment = " + selectedDepartment);
    }
}