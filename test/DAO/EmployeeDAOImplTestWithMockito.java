/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Employee;
import DTO.EmployeeDetails;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mindrot.jbcrypt.BCrypt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author QAQ
 */
public class EmployeeDAOImplTestWithMockito {

    Connection conn = mock(Connection.class);
    Statement stmt = mock(Statement.class);
    ResultSet rs = mock(ResultSet.class);
    EmployeeDetails ed1 = new EmployeeDetails(1, 1, "19970214", "123@gmail.com", "Ireland");
    Employee employee = new Employee(1, "D00198320", "JI", "admin", 1, "HR", 1,
            "Management", 1, "", ed1);
    EmployeeDetails ed2 = new EmployeeDetails(1, 1, "19970214", "123@gmail.com", "Ireland");
    Employee employee1 = new Employee(1, "D00198320", "JI", "admin", 1, "HR", 1,
            "Management", 1, "", ed2);
    EmployeeDetails ed3 = new EmployeeDetails(1, 1, "19970214", "123@gmail.com", "Ireland");
    Employee employee2 = new Employee(1, "D00198320", "JI", "admin", 1, "HR", 1,
            "Management", 1, "", ed3);

    public EmployeeDAOImplTestWithMockito() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of register method, of class EmployeeDAOImpl.
     */
    @Test
    public void testRegister() throws Exception {

        String employee_account = employee.getEmployee_account();
        String employee_name = employee.getEmployee_name();
        int department_id = employee.getDepartment_id();
        int position_id = employee.getPosition_id();
        String password = employee.getPassword();
        int status = employee.getStatus();
        String date = employee.getDate();

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

        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(query)).thenReturn(rs);
        when(rs.next()).thenReturn(false);

        when(stmt.executeUpdate(sql)).thenReturn(1);

        ResultSet rs2 = mock(ResultSet.class);
        when(stmt.executeQuery(selectSql)).thenReturn(rs2);
        when(rs2.next()).thenReturn(true, false);
        when(rs2.getInt("LAST_INSERT_ID()")).thenReturn(employee.getEmployee_id());

        EmployeeDAOImpl instance = new EmployeeDAOImpl(conn);
        int expResult = employee.getEmployee_id();
        int result = instance.register(employee_account, employee_name, department_id, position_id, password, status, date);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class EmployeeDAOImpl.
     */
    @Test
    public void testLogin() throws Exception {

        String employee_account = employee.getEmployee_account();
        String password = employee.getPassword();

        String sql = "SELECT * FROM `employee` WHERE `employee_account` = \"" + employee_account + "\";";

        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(sql)).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getString("password")).thenReturn(
                BCrypt.hashpw(Util.Utils.replceSymbol(employee.getPassword()),
                        BCrypt.gensalt()));
        when(rs.getInt("employee_id")).thenReturn(employee.getEmployee_id());

        EmployeeDAOImpl instance = new EmployeeDAOImpl(conn);
        Employee expResult = employee;
        Employee result = instance.login(employee_account, password);
        assertEquals(expResult.getEmployee_id(), result.getEmployee_id());
    }

    /**
     * Test of getAllEMployees method, of class EmployeeDAOImpl.
     */
    @Test
    public void testGetAllEMployees() throws Exception {

        List<Employee> expResult = new ArrayList();
        expResult.add(employee);
        expResult.add(employee1);
        expResult.add(employee2);

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

        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(sql)).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("employee_id")).thenReturn(employee.getEmployee_id(), employee1.getEmployee_id(), employee2.getEmployee_id());
        when(rs.getString("employee_account")).thenReturn(employee.getEmployee_account(), employee1.getEmployee_account(), employee2.getEmployee_account());
        when(rs.getString("employee_name")).thenReturn(employee.getEmployee_name(), employee1.getEmployee_name(), employee2.getEmployee_name());
        when(rs.getInt("position_id")).thenReturn(employee.getPosition_id(), employee1.getPosition_id(), employee2.getPosition_id());
        when(rs.getInt("department_id")).thenReturn(employee.getDepartment_id(), employee1.getDepartment_id(), employee2.getDepartment_id());
        when(rs.getString("department_name")).thenReturn(employee.getDepartment_name(), employee1.getDepartment_name(), employee2.getDepartment_name());
        when(rs.getString("position_name")).thenReturn(employee.getPosition_name(), employee1.getPosition_name(), employee2.getPosition_name());
        when(rs.getInt("status")).thenReturn(employee.getStatus(), employee1.getStatus(), employee2.getStatus());
        when(rs.getInt("genre")).thenReturn(employee.getEmployee_details().getGenre(), employee1.getEmployee_details().getGenre(), employee2.getEmployee_details().getGenre());
        when(rs.getString("birthday")).thenReturn(employee.getEmployee_details().getBirthday(), employee1.getEmployee_details().getBirthday(), employee2.getEmployee_details().getBirthday());
        when(rs.getString("email")).thenReturn(employee.getEmployee_details().getEmail(), employee1.getEmployee_details().getEmail(), employee2.getEmployee_details().getEmail());
        when(rs.getString("address")).thenReturn(employee.getEmployee_details().getAddress(), employee1.getEmployee_details().getAddress(), employee2.getEmployee_details().getAddress());
        when(rs.getString("date")).thenReturn(employee.getDate(), employee1.getDate(), employee2.getDate());

        EmployeeDAOImpl instance = new EmployeeDAOImpl(conn);

        int numOfEmployeeList = 3;
        List<Employee> result = instance.getAllEMployees();
        assertEquals(numOfEmployeeList, result.size());
    }

    /**
     * Test of updateEmployee method, of class EmployeeDAOImpl.
     */
    @Test
    public void testUpdateEmployee() throws Exception {

        int position_id = employee.getPosition_id();
        int department_id = employee.getDepartment_id();
        int status = employee.getStatus();
        String date = employee.getDate();
        int employee_id = employee.getEmployee_id();

        String query = "UPDATE `employee` SET "
                + "`position_id`=" + position_id + ","
                + "`department_id`=" + department_id + ","
                + "`status`=" + status + ","
                + "`date`=\"" + date + "\" "
                + "WHERE "
                + "`employee_id` = " + employee_id;

        when(conn.createStatement()).thenReturn(stmt);
        when(stmt.executeUpdate(query)).thenReturn(1);

        EmployeeDAOImpl instance = new EmployeeDAOImpl(conn);
        int expResult = 1;
        int result = instance.updateEmployee(employee_id, department_id, position_id, status, date);
        assertEquals(expResult, result);

    }

}
