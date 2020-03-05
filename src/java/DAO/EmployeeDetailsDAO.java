/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.EmployeeDetails;

/**
 *
 * @author QAQ
 */
public interface EmployeeDetailsDAO {

    public int setEmployeeDetails(int employee_id, int genre, String birthday, String email, String address) throws Exception;

    public EmployeeDetails getEmployeeDetails(int employee_id) throws Exception;

    public int updateEmployeeDetails(int employee_id, int genre, String birthday, String email, String address) throws Exception;

}
