/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author QAQ
 */
public class Employee {

    private int employee_id;
    private String employee_account;
    private String employee_name;
    private String password;
    private int position_id;
    private String position_name;
    private int department_id;
    private String department_name;
    private int status;
    private String date;
    private EmployeeDetails employee_details;

    public Employee() {
    }

    public String getEmployee_account() {
        return employee_account;
    }

    public void setEmployee_account(String employee_account) {
        this.employee_account = employee_account;
    }

    public Employee(int employee_id, String employee_account, String employee_name, String password, int position_id,
            String position_name, int department_id,
            String department_name, int status, String date, EmployeeDetails employee_details) {
        this.employee_account = employee_account;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.password = password;
        this.position_id = position_id;
        this.position_name = position_name;
        this.department_id = department_id;
        this.department_name = department_name;
        this.status = status;
        this.date = date;
        this.employee_details = employee_details;

    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("employee_id", this.employee_id);
        json.put("employee_account", this.employee_account);
        json.put("employee_name", this.employee_name);
        json.put("password", this.password);
        json.put("position_id", this.position_id);
        json.put("position_name", this.position_name);
        json.put("department_id", this.department_id);
        json.put("department_name", this.department_name);
        json.put("status", this.status);
        json.put("date", this.date);
        if (this.employee_details != null) {
            json.put("genre", this.employee_details.getGenre());
            json.put("birthday", this.employee_details.getBirthday());
            json.put("address", this.employee_details.getAddress());
            json.put("email", this.employee_details.getEmail());
        }
        return json;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public int getStatus() {
        return status;
    }

    public EmployeeDetails getEmployee_details() {
        return employee_details;
    }

    public void setEmployee_details(EmployeeDetails employee_details) {
        this.employee_details = employee_details;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
