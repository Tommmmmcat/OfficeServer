/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.EmployeeDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author QAQ
 */
public class EmployeeDetailsDAOImpl implements EmployeeDetailsDAO {

    private Connection conn;

    public EmployeeDetailsDAOImpl() throws Exception {
        conn = DataBaseDAO.getInstance().connect();
    }

    public EmployeeDetailsDAOImpl(Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection is NULL");
        }
        this.conn = conn;
    }

    @Override
    public int setEmployeeDetails(int employee_id, int genre, String birthday, String email, String address) throws Exception {
        if (employee_id < 1 || genre < 0 || genre > 1) {
            throw new Exception("Invalid Parameter(s)");
        }
        if (birthday == null) {
            birthday = "";
        }
        if (email == null) {
            email = "";
        }
        if (address == null) {
            address = "";
        }
        int eid = 0;
        String query = "SELECT * FROM `employee_details` WHERE `employee_id` = " + employee_id;
        String sql = "INSERT INTO `employee_details`"
                + "(`employee_id`, `genre`, `birthday`, `email`, `address`) "
                + "VALUES ("
                + +employee_id + ","
                + genre + ","
                + "\"" + birthday + "\","
                + "\"" + email + "\","
                + "\"" + address + "\""
                + ")";
        String selectSql = "SELECT LAST_INSERT_ID();";
        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(query);
            if (!rs.next()) {
                if (stmt.executeUpdate(sql) == 1) {
                    rs = stmt.executeQuery(selectSql);
                    while (rs.next()) {
                        eid = rs.getInt("LAST_INSERT_ID()");
                    }
                }
            } else {
                eid = -1;
                throw new Exception("User Already Exists");
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }

        return eid;
    }

    @Override
    public EmployeeDetails getEmployeeDetails(int employee_id) throws Exception {
        if (employee_id < 1) {
            throw new Exception("Invalid Employee ID");
        }
        EmployeeDetails empdl = new EmployeeDetails();
        String query = "SELECT * FROM `employee_details` WHERE `employee_id` = " + employee_id;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                empdl.setEmployee_id(employee_id);
                empdl.setGenre(rs.getInt("genre"));
                empdl.setAddress(rs.getString("address"));
                empdl.setBirthday(rs.getString("birthday"));
                empdl.setEmail(rs.getString("email"));
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }
        return empdl;
    }

    @Override
    public int updateEmployeeDetails(int employee_id, int genre, String birthday, String email, String address) throws Exception {
        if (employee_id < 1 || genre < 0 || genre > 1) {
            throw new Exception("Invalid Parameter(s)");
        }
        if (birthday == null) {
            birthday = "";
        }
        if (email == null) {
            email = "";
        }
        if (address == null) {
            address = "";
        }
        int updateLine;
        String query = "UPDATE `employee_details` "
                + "SET "
                + "`genre`=" + genre + ","
                + "`birthday`=\"" + birthday + "\","
                + "`email`=\"" + email + "\","
                + "`address`=\"" + address + "\" "
                + "WHERE `employee_id`= " + employee_id;
        try (Statement stmt = conn.createStatement()) {
            updateLine = stmt.executeUpdate(query);
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
        }
        return updateLine;
    }

}
