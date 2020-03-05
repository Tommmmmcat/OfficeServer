/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Position;
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
public class PositionDAOImplTestWithMockito {

    Connection conn = mock(Connection.class);
    Statement stmt = mock(Statement.class);
    ResultSet rs = mock(ResultSet.class);

    Position p1 = new Position(1, "Manager");
    Position p2 = new Position(2, "casher");
    Position p3 = new Position(3, "Manager");
    Position p4 = new Position(4, "doctor");

    ArrayList<Position> pList = new ArrayList();

    public PositionDAOImplTestWithMockito() {
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);
        pList.add(p4);
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
     * Test of getAllPositionByDeptId method, of class PositionDAOImpl.
     */
    @Test
    public void testGetAllPositionByDeptId() throws Exception {
        int departmentId = 1;
        String query = "SELECT * FROM `position` WHERE `department_id` = " + departmentId;

        when(this.conn.createStatement()).thenReturn(this.stmt);
        when(this.stmt.executeQuery(query)).thenReturn(this.rs);
        when(rs.next()).thenReturn(true, true, true, true, false);

        when(rs.getInt("position_id")).thenReturn(p1.getPositionId(), p2.getPositionId(), p3.getPositionId(), p4.getPositionId());
        when(rs.getString("position_name")).thenReturn(p1.getPositionName(), p2.getPositionName(), p3.getPositionName(), p4.getPositionName());

        PositionDAOImpl instance = new PositionDAOImpl(conn);
        List<Position> result = instance.getAllPositionByDeptId(departmentId);

        int numOfPostionList = 4;
        assertEquals(numOfPostionList, result.size());

        assertEquals(pList.get(0), result.get(0));
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getPositionById method, of class PositionDAOImpl.
     */
    @Test
    public void testGetPositionById() throws Exception {
        int positionId = 1;
        String query = "SELECT * FROM `position` WHERE `position_id` = " + positionId;
        when(this.conn.createStatement()).thenReturn(this.stmt);
        when(this.stmt.executeQuery(query)).thenReturn(this.rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("position_id")).thenReturn(p1.getPositionId());
        when(rs.getString("position_name")).thenReturn(p1.getPositionName());

        PositionDAOImpl instance = new PositionDAOImpl(conn);
        Position expResult = p1;
        Position result = instance.getPositionById(positionId);
        assertEquals(expResult, result);
    }

}
