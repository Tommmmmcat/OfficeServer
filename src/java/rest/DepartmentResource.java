/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DAO.DepartmentDAO;
import DAO.DepartmentDAOImpl;
import DTO.Department;
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
@Path("Department")
public class DepartmentResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DepartmentResource
     */
    public DepartmentResource() {
    }

    /**
     * Retrieves representation of an instance of rest.DepartmentResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        JSONArray jsonArray = new JSONArray();
        String status = "0";
        String error = "null";
        try {
            DepartmentDAO dept = new DepartmentDAOImpl();
            List<Department> deptList = dept.getAllDepartments();
            for (Department d : deptList) {
                jsonArray.add(d.getJSONObject());
            }
            status = "1";
        } catch (Exception ex) {
            status = "-1";
            error = ex.toString();
        }
        return BeJson.getJson(status, error, jsonArray).toJSONString();

    }

    /**
     * PUT method for updating or creating an instance of DepartmentResource
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
        JSONArray jsonArray = new JSONArray();
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content);
            JSONObject dataJson = (JSONObject) parser.parse(json.get("data").toString());
            int department_id = Integer.parseInt(dataJson.get("department_id").toString());
            DepartmentDAO deptDao = new DepartmentDAOImpl();
            Department dept = deptDao.getDepartmentById(department_id);
            JSONObject jsonObj = dept.getJSONObject();
            jsonArray.add(jsonObj);
        } catch (Exception ex) {
            status = "-1";
            error = ex.toString();
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BeJson.getJson(status, error, jsonArray).toJSONString();
    }
}
