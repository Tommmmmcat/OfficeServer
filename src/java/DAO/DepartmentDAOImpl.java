/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Department;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QAQ
 */
public class DepartmentDAOImpl implements DepartmentDAO {

    private Connection conn;

    public DepartmentDAOImpl() throws Exception {
        this.conn = DataBaseDAO.getInstance().connect();
    }

    public DepartmentDAOImpl(Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection is NULL");
        }
        this.conn = conn;
    }

    @Override
    public void addNewDepartment() throws Exception {
    }

    @Override
    public List<Department> getAllDepartments() throws Exception {
        String query = "SELECT * FROM `department`";
        ResultSet rs;
        List<Department> deptList;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(query);
            deptList = new ArrayList();
            while (rs.next()) {
                Department dept = new Department();
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
                deptList.add(dept);
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }
        return deptList;
    }

    @Override
    public Department getDepartmentById(int departmentId) throws Exception {
        if (departmentId < 1) {
            throw new Exception("Bad Department ID");
        }
        String query = "SELECT * FROM `department` WHERE `department_id` = " + departmentId;
        ResultSet rs;
        Department dept;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(query);
            dept = new Department();
            if (rs.next()) {
                dept.setDepartmentId(rs.getInt("department_id"));
                dept.setDepartmentName(rs.getString("department_name"));
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }
        return dept;
    }

}
