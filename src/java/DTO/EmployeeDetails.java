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
public class EmployeeDetails {

    private int employee_id;
    private int genre;//0 female, 1 male
    private String birthday;
    private String email;
    private String address;

    public EmployeeDetails() {
    }

    public EmployeeDetails(int employee_id, int genre, String birthday, String email, String address) {
        this.employee_id = employee_id;
        this.genre = genre;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("employee_id", this.employee_id);
        json.put("genre", this.genre);
        json.put("birthday", this.birthday);
        json.put("email", this.email);
        json.put("address", this.address);
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
        final EmployeeDetails other = (EmployeeDetails) obj;
        if (!Objects.equals(this.employee_id, other.employee_id)) {
            return false;
        }
        return true;
    }
}
