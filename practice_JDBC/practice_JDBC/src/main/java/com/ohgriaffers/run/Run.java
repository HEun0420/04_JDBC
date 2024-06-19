package com.ohgriaffers.run;

import com.ohgriaffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgriaffers.common.JDBCTemplate.close;
import static com.ohgriaffers.common.JDBCTemplate.getConnection;

public class Run {
    public static void main(String[] args) {
 
        // 5가지 중에 하나 선택하기, 0번으로 나가기 선택 => while문 으로 작성
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("employee 데이터에 접속하셨습니다. 어느 것을 실행할까요?.");
            System.out.println("1. 목록 조회");
            System.out.println("2. 추가");
            System.out.println("3. 수정");
            System.out.println("4. 삭제");
            System.out.println("5. 단일조회");
            System.out.println("0. 나가기");

            String choice = sc.next();


            switch (choice) {
                case "1":
                    // 목록조회 메서드
                    allSearching();
                    break;
                case "2":
                    // 추가 메서드
                    addInfo();
                    break;
                case "3":
                    // 수정 메서드
                    editInfo();
                    break;
                case "4":
                    // 삭제 메서드
                    deleteInfo();
                    break;
                case "5":
                    // 단일조회 메서드
                    oneSearching();
                    break;
                case "0":
                    flag = false;
                    System.out.println("종료합니다.");
                    break;
            }
        }

    }

    public static void allSearching() {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;


        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream(
                    "src/main/java/com/ohgriaffers/mapper/employee-query.xml"));
            String query = prop.getProperty("selectAllSearching");
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()) {
                row = new EmployeeDTO();

                row.setEmpID(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        for (EmployeeDTO emp : empList) {
            System.out.println(emp);
        }
    }

    public static void addInfo() {

        Connection con = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream(
                    "src/main/java/com/ohgriaffers/mapper/employee-query.xml"));

            String query = prop.getProperty("selectAddInfo");

            pstmt = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);

            System.out.println("직원의 id를 입력해주세요.");
            String emp_id = sc.nextLine();
            System.out.println("직원의 이름을 입력해주세요.");
            String emp_name = sc.nextLine();
            System.out.println("직원의 번호를 입력해주세요.");
            String emp_no = sc.nextLine();
            System.out.println("직원의 이메일을 입력해주세요.");
            String email = sc.nextLine();
            System.out.println("직원의 핸드폰 번호를 입력해주세요.");
            String phone = sc.nextLine();
            System.out.println("직원의 부서 코드를 입력해주세요.");
            String dept_code = sc.nextLine();
            System.out.println("직원의 직장 코드를 입력해주세요.");
            String job_code = sc.nextLine();
            System.out.println("직원의 월급 레벨을 입력해주세요.");
            String sal_level = sc.nextLine();
            System.out.println("직원의 월급을 입력해주세요.");
            Double salary = sc.nextDouble();
            System.out.println("직원의 보너스를 입력해주세요.");
            Double bonus = sc.nextDouble();
            sc.nextLine();
            System.out.println("직원의 매니저 ID를 입력해주세요.");
            String managerId = sc.nextLine();

            EmployeeDTO newEmp = new EmployeeDTO();
            newEmp.setEmpID(emp_id);
            newEmp.setEmpName(emp_name);
            newEmp.setEmpNo(emp_no);
            newEmp.setEmail(email);
            newEmp.setPhone(phone);
            newEmp.setDeptCode(dept_code);
            newEmp.setJobCode(job_code);
            newEmp.setSalLevel(sal_level);
            newEmp.setSalary(salary);
            newEmp.setBonus(bonus);
            newEmp.setManagerId(managerId);


            pstmt.setString(1, newEmp.getEmpID());
            pstmt.setString(2, newEmp.getEmpName());
            pstmt.setString(3, newEmp.getEmpNo());
            pstmt.setString(4, newEmp.getEmail());
            pstmt.setString(5, newEmp.getPhone());
            pstmt.setString(6, newEmp.getDeptCode());
            pstmt.setString(7, newEmp.getJobCode());
            pstmt.setString(8, newEmp.getSalLevel());
            pstmt.setDouble(9, newEmp.getSalary());
            pstmt.setDouble(10, newEmp.getBonus());
            pstmt.setString(11, newEmp.getManagerId());


            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 정보 저장 성공");
        } else {
            System.out.println("직원 정보 저장 실패!");

        }
    }

    public static void editInfo() {
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 직원의 id를 입력해주세요.");
        String emp_id = sc.nextLine();
        System.out.println("변경할 직원의 이름을 입력해주세요.");
        String emp_name = sc.nextLine();
        System.out.println("변경할 직원의 번호를 입력해주세요.");
        String emp_no = sc.nextLine();
        System.out.println("변경할 직원의 이메일을 입력해주세요.");
        String email = sc.nextLine();
        System.out.println("변경할 직원의 핸드폰 번호를 입력해주세요.");
        String phone = sc.nextLine();
        System.out.println("변경할 직원의 부서 코드를 입력해주세요.");
        String dept_code = sc.nextLine();
        System.out.println("변경할 직원의 직장 코드를 입력해주세요.");
        String job_code = sc.nextLine();
        System.out.println("변경할 직원의 월급 레벨을 입력해주세요.");
        String sal_level = sc.nextLine();
        System.out.println("변경할 직원의 월급을 입력해주세요.");
        Double salary = sc.nextDouble();
        System.out.println("변경할 직원의 보너스를 입력해주세요.");
        Double bonus = sc.nextDouble();
        sc.nextLine();
        System.out.println("변경할 직원의 매니저 ID를 입력해주세요.");
        String managerId = sc.nextLine();

        EmployeeDTO changedEmp = new EmployeeDTO();
        changedEmp.setEmpID(emp_id);
        changedEmp.setEmpName(emp_name);
        changedEmp.setEmpNo(emp_no);
        changedEmp.setEmail(email);
        changedEmp.setPhone(phone);
        changedEmp.setDeptCode(dept_code);
        changedEmp.setJobCode(job_code);
        changedEmp.setSalLevel(sal_level);
        changedEmp.setSalary(salary);
        changedEmp.setBonus(bonus);
        changedEmp.setManagerId(managerId);


        try {
            prop.loadFromXML(new FileInputStream(
                    "src/main/java/com/ohgriaffers/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEditInfo");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, changedEmp.getEmpID());
            pstmt.setString(2, changedEmp.getEmpName());
            pstmt.setString(3, changedEmp.getEmpNo());
            pstmt.setString(4, changedEmp.getEmail());
            pstmt.setString(5, changedEmp.getPhone());
            pstmt.setString(6, changedEmp.getDeptCode());
            pstmt.setString(7, changedEmp.getJobCode());
            pstmt.setString(8, changedEmp.getSalLevel());
            pstmt.setDouble(9, changedEmp.getSalary());
            pstmt.setDouble(10, changedEmp.getBonus());
            pstmt.setString(11, changedEmp.getManagerId());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 정보 변경 성공");
        } else {
            System.out.println("직원 정보 변경 실패!");
        }
    }


    public static void deleteInfo() {
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();
        try {

            prop.loadFromXML(
                    new FileInputStream(
                            "src/main/java/com/ohgriaffers/mapper/employee-query.xml"
                    )
            );

            String query = prop.getProperty("selectDeleteInfo");

            Scanner sc = new Scanner(System.in);
            System.out.println("삭제할 직원의 ID를 입력하세요 : ");
            String emp_id = String.valueOf(sc.nextInt());

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(emp_id));

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if (result > 0) {
            System.out.println("직원 정보 삭제 성공!!");
        } else {
            System.out.println("직원 정보 삭제 실패");
        }
    }


    public static void oneSearching() {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);

        System.out.println("조회할 직원의 ID를 입력해 주세요 : ");
        String empId = sc.nextLine();

        EmployeeDTO selectedEmp = null;

        try {
            prop.loadFromXML(new FileInputStream(
                    "src/main/java/com/ohgriaffers/mapper/employee-query.xml"));
            String query = prop.getProperty("selectOneSearching");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                selectedEmp = new EmployeeDTO();

                selectedEmp.setEmpID(rset.getString("EMP_ID"));
                selectedEmp.setEmpName(rset.getString("EMP_NAME"));
                selectedEmp.setEmpNo(rset.getString("EMP_NO"));
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
        } catch (InvalidPropertiesFormatException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }
        System.out.println("조회한 직원은 " + selectedEmp);
    }
}