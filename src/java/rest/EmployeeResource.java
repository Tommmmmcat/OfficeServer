/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import DTO.Employee;
import Util.BeJson;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * REST Web Service
 *
 * @author QAQ
 */
@Path("Employee")
public class EmployeeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeResource
     */
    public EmployeeResource() {
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        String status = "0";
        String error = "null";
        JSONArray jsonArray = null;
        try {
            EmployeeDAO empDAO = new EmployeeDAOImpl();
            List<Employee> empList = empDAO.getAllEMployees();
            if (empList != null) {
                jsonArray = new JSONArray();
                status = "1";
                for (Employee emp : empList) {
                    jsonArray.add(emp.getJSONObject());
                }
            } else {
                status = "-1";
                error = "List<Employee> NULL ERROR";
            }
        } catch (Exception ex) {
            status = "-1";
            error = ex.toString();
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return BeJson.getJson(status, error, jsonArray).toJSONString();
    }

    /**
     * PUT method for updating or creating an instance of EmployeeResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {

    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public String postText(String content) {
        String status = "0";
        String error = "null";
        JSONArray jsonArray = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content);
            JSONObject dataJson = (JSONObject) parser.parse(json.get("data").toString());
            EmployeeDAO empDAO = new EmployeeDAOImpl();
            String method = json.get("method").toString();
            switch (method) {
                case "register":
                    int eid = empDAO.register(
                            dataJson.get("employee_account").toString(),
                            dataJson.get("employee_name").toString(),
                            Integer.parseInt(dataJson.get("department_id").toString()),
                            Integer.parseInt(dataJson.get("position_id").toString()),
                            dataJson.get("password").toString(),
                            Integer.parseInt(dataJson.get("status").toString()),
                            dataJson.get("date").toString()
                    );
                    if (eid != -1) {
                        status = "1";
                        JSONObject resultJson = new JSONObject();
                        resultJson.put("employee_id", eid);
                        jsonArray = new JSONArray();
                        jsonArray.add(resultJson);
                    }
                    break;
                case "login":
                    Employee emp = empDAO.login(dataJson.get("employee_account").toString(), dataJson.get("password").toString());
                    if (emp != null) {
                        status = "1";
                        jsonArray = new JSONArray();
                        jsonArray.add(emp.getJSONObject());
                    }
                    break;
                case "update":
                    int updateLine = empDAO.updateEmployee(Integer.parseInt(dataJson.get("employee_id").toString()),
                            Integer.parseInt(dataJson.get("department_id").toString()),
                            Integer.parseInt(dataJson.get("position_id").toString()),
                            Integer.parseInt(dataJson.get("status").toString()),
                            dataJson.get("date").toString());
                    if (updateLine == 1) {
                        status = "1";
                    } else {
                        status = "-1";
                        error = "Update Failed!";
                    }
                    break;
                default:
                    status = "-1";
                    error = "NO METHOD HAS BEEN CHOOSED!";
                    break;
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
            status = "-1";
            error = ex.toString();
        }
        return BeJson.getJson(status, error, jsonArray).toJSONString();
    }

}
