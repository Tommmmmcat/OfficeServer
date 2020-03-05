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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author QAQ
 */
public class DepartmentDAOImplTestWithMockito {

    Connection conn = mock(Connection.class);
    Statement stmt = mock(Statement.class);
    ResultSet rs = mock(ResultSet.class);
    Department d1 = new Department(1, "Department_1");
    Department d2 = new Department(2, "Department_2");
    Department d3 = new Department(3, "Department_3");
    ArrayList<Department> expResultList = new ArrayList();

    public DepartmentDAOImplTestWithMockito() {
        expResultList.add(d1);
        expResultList.add(d2);
        expResultList.add(d3);
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
     * Test of getAllDepartments method, of class DepartmentDAOImpl.
     */
    @Test
    public void testGetAllDepartments() throws Exception {

        String query = "SELECT * FROM `department`";

        when(this.conn.createStatement()).thenReturn(this.stmt);
        when(this.stmt.executeQuery(query)).thenReturn(this.rs);
        when(rs.next()).thenReturn(true, true, true, false);
        when(rs.getInt("department_id")).thenReturn(d1.getDepartmentId(), d2.getDepartmentId(), d3.getDepartmentId());
        when(rs.getString("department_name")).thenReturn(d1.getDepartmentName(), d2.getDepartmentName(), d2.getDepartmentName());

        int numProductsInTable = expResultList.size();

        DepartmentDAO instance = new DepartmentDAOImpl(this.conn);
        List<Department> result = instance.getAllDepartments();

        assertEquals(numProductsInTable, result.size());
        
        assertEquals(expResultList.get(0), result.get(0));
        
        assertEquals(expResultList, result);
    }

    /**
     * Test of getDepartmentById method, of class DepartmentDAOImpl.
     */
    @Test
    public void testGetDepartmentById() throws Exception {
        int departmentId = 1;
        String query = "SELECT * FROM `department` WHERE `department_id` = " + departmentId;
        Department expResult = expResultList.get(0);

        when(this.conn.createStatement()).thenReturn(this.stmt);
        when(this.stmt.executeQuery(query)).thenReturn(this.rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("department_id")).thenReturn(expResultList.get(0).getDepartmentId());
        when(rs.getString("department_name")).thenReturn(expResultList.get(0).getDepartmentName());

        DepartmentDAOImpl instance = new DepartmentDAOImpl(this.conn);

        Department result = instance.getDepartmentById(departmentId);
        assertEquals(expResult.getDepartmentName(), result.getDepartmentName());
    }

}
