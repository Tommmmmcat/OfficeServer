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

/**
 *
 * @author QAQ
 */
public class PositionDAOImpl implements PositionDAO {

    private Connection conn;

    public PositionDAOImpl() throws Exception {
        conn = DataBaseDAO.getInstance().connect();
    }

    public PositionDAOImpl(Connection conn) throws Exception {
        if (conn == null) {
            throw new Exception("Connection is NULL");
        }
        this.conn = conn;
    }

    @Override
    public List<Position> getAllPositionByDeptId(int departmentId) throws Exception {
        if (departmentId < 1) {
            throw new Exception("Bad Department ID");
        }
        String query = "SELECT * FROM `position` WHERE `department_id` = " + departmentId;
        ResultSet rs;
        List<Position> posList;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(query);
            posList = new ArrayList();
            while (rs.next()) {
                Position pos = new Position();
                pos.setPositionId(rs.getInt("position_id"));
                pos.setPositionName(rs.getString("position_name"));
                posList.add(pos);
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }

        return posList;

    }

    @Override
    public Position getPositionById(int positionId) throws Exception {
        if (positionId < 1) {
            throw new Exception("Bad Position ID");
        }
        String query = "SELECT * FROM `position` WHERE `position_id` = " + positionId;
        ResultSet rs;
        Position pos;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(query);
            pos = new Position();
            if (rs.next()) {
                pos.setPositionId(rs.getInt("position_id"));
                pos.setPositionName(rs.getString("position_name"));
            }
            conn.close();
            DataBaseDAO.getInstance().close();
            stmt.close();
            rs.close();
        }
        return pos;
    }

}
