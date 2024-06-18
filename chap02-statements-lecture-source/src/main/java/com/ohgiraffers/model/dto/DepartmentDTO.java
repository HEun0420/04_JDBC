package com.ohgiraffers.model.dto;

public class DepartmentDTO {


    private String DeptID;
    private String DeptTitle;
    private String LocationId;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String deptID, String deptTitle, String locationId) {
        DeptID = deptID;
        DeptTitle = deptTitle;
        LocationId = locationId;
    }

    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }

    public String getDeptTitle() {
        return DeptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        DeptTitle = deptTitle;
    }

    public String getLocationId() {
        return LocationId;
    }

    public void setLocationId(String locationId) {
        LocationId = locationId;
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "DeptID='" + DeptID + '\'' +
                ", DeptTitle='" + DeptTitle + '\'' +
                ", LocationId='" + LocationId + '\'' +
                '}';
    }
}