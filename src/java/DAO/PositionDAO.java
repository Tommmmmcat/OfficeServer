/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Position;
import java.util.List;

/**
 *
 * @author QAQ
 */
public interface PositionDAO {

    public List<Position> getAllPositionByDeptId(int departmentId) throws Exception;

    public Position getPositionById(int positionId) throws Exception;

}
