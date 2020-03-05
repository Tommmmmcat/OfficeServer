/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Department;
import java.util.List;

/**
 *
 * @author QAQ
 */
public interface DepartmentDAO {

    public List<Department> getAllDepartments() throws Exception;

    public Department getDepartmentById(int departmentId) throws Exception;

    public void addNewDepartment() throws Exception;

}
