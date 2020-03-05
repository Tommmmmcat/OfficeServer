/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Objects;
import org.json.simple.JSONObject;

/**
 *
 * @author QAQ
 */
public class Department {

    private int department_id;
    private String department_name;

    public Department() {
    }

    public Department(int department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public int getDepartmentId() {
        return department_id;
    }

    public void setDepartmentId(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentName() {
        return department_name;
    }

    public void setDepartmentName(String department_name) {
        this.department_name = department_name;
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("department_id", department_id);
        json.put("department_name", department_name);
        return json;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Department other = (Department) obj;
        if (!Objects.equals(this.department_id, other.department_id)) {
            return false;
        }
        if (!Objects.equals(this.department_name, other.department_name)) {
            return false;
        }
        return true;
    }

}
