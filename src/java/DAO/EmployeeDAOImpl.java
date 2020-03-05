/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Employee;
import DTO.EmployeeDetails;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author QAQ
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection conn;

    public EmployeeDAOImpl() throws Exception {
        conn = DataBaseDAO.getInstance().connect();
    }

    public EmployeeDAOImpl(Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection is NULL");
        }
        this.conn = conn;
    }

    @Override
    public int register(String employee_account, String employee_name, int department_id, int position_id, String password, int status, String date) throws Exception {
        if (employee_account == null || employee_name == null || password == null) {
            throw new Exception("Missing Required Parameters");
        }
        if (employee_account.equals("") || employee_name.equals("") || department_id < 0 || position_id < 0 || password.equals("") || status < 0) {
            throw new Exception("Missing Required Parameters");
        }
        if (date == null) {
            date = "";
        }
        employee_account = Util.Utils.replceSymbol(employee_account);
        password = BCrypt.hashpw(Util.Utils.replceSymbol(password), BCrypt.gensalt());
        int eid = 0;
        String query = "SELECT * FROM `employee` WHERE employee_account = \"" + employee_account + "\"";
        String sql = "INSERT INTO `employee`("
                + "`employee_account`,"
                + "`employee_name`, "
                + "`department_id`, "
                + "`position_id`, "
                + "`password`,"
                + "`status`,"
                + "`date`"
                + ") VALUES "
                + "(\"" + employee_account + "\","
                + "\"" + employee_name + "\","
                + "\"" + department_id + "\","
                + "\"" + position_id + "\","
                + "\"" + password + "\","
                + "\"" + status + "\","
                + "\"" + date + "\""
                + ");";
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
                } else {
                    throw new Exception("Register Failed!");
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
    public Employee login(String employee_account, String password) throws Exception {
        if (employee_account == null || employee_account.equals("") || password == null || password.equals("")) {
            throw new Exception("Missing Required Parameters");
        }
        Employee emp = null;
        String psw = "";
        employee_account = Util.Utils.replceSymbol(employee_account);
        password = Util.Utils.replceSymbol(password);
        int eid = -1;
        String sql = "SELECT * FROM `employee` WHERE `employee_account` = \"" + employee_account + "\";";
        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                psw = rs.getString("password");
                eid = rs.getInt("employee_id");
            }
            if (BCrypt.checkpw(password, psw)) {
                emp = new Employee();
                emp.setEmployee_id(eid);
            } else {
                throw new Exception("Wrong Password!");
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }

        return emp;
    }

    @Override
    public List<Employee> getAllEMployees() throws Exception {
        List<Employee> empList = new ArrayList();
        String sql = "SELECT \n"
                + "`employee`.`employee_id`,\n"
                + "`employee`.`employee_account`,\n"
                + "`employee`.`employee_name`, \n"
                + "`employee`.`status`,\n"
                + "`employee`.`department_id`,\n"
                + "`employee`.`position_id`,\n"
                + "`employee`.`date`,\n"
                + "`department`.`department_name`,\n"
                + "`position`.`position_name`,\n"
                + "`employee_details`.`genre`,\n"
                + "`employee_details`.`birthday`,\n"
                + "`employee_details`.`email`,\n"
                + "`employee_details`.`address`\n"
                + "FROM `employee`\n"
                + "LEFT JOIN `department` ON `department`.`department_id` = `employee`.`department_id` \n"
                + "LEFT JOIN `position` ON `position`.`position_id` = `employee`.`position_id`\n"
                + "LEFT JOIN `employee_details` ON `employee_details`.`employee_id` = `employee`.`employee_id`";
        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmployee_id(rs.getInt("employee_id"));
                emp.setEmployee_account(rs.getString("employee_account") != null ? rs.getString("employee_account") : "");
                emp.setEmployee_name(rs.getString("employee_name") != null ? rs.getString("employee_name") : "");
                emp.setPosition_id(rs.getInt("position_id"));
                emp.setDepartment_id(rs.getInt("department_id"));
                emp.setDepartment_name(rs.getString("department_name") != null ? rs.getString("department_name") : "");
                emp.setPosition_name(rs.getString("position_name") != null ? rs.getString("position_name") : "");
                emp.setDate(rs.getString("date") != null ? rs.getString("date") : "");
                emp.setStatus(rs.getInt("status"));
                EmployeeDetails ed = new EmployeeDetails();
                ed.setGenre(rs.getInt("genre"));
                ed.setBirthday(rs.getString("birthday") != null ? rs.getString("birthday") : "");
                ed.setEmail(rs.getString("email") != null ? rs.getString("email") : "");
                ed.setAddress(rs.getString("address") != null ? rs.getString("address") : "");
                emp.setEmployee_details(ed);
                empList.add(emp);
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }

        return empList;
    }

    @Override
    public int updateEmployee(int employee_id, int department_id, int position_id, int status, String date) throws Exception {
        if (employee_id < 0 || department_id < 0 || position_id < 0 || status < 0) {
            throw new Exception("Wrong Required Parameters");
        }
        if (date == null) {
            date = "";
        }
        String query = "UPDATE `employee` SET "
                + "`position_id`=" + position_id + ","
                + "`department_id`=" + department_id + ","
                + "`status`=" + status + ","
                + "`date`=\"" + date + "\" "
                + "WHERE "
                + "`employee_id` = " + employee_id;
        int updateLine;
        try (Statement stmt = conn.createStatement()) {
            updateLine = stmt.executeUpdate(query);
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
        }
        return updateLine;

    }

}
