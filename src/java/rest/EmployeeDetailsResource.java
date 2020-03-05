/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DAO.EmployeeDAO;
import DAO.EmployeeDAOImpl;
import DAO.EmployeeDetailsDAO;
import DAO.EmployeeDetailsDAOImpl;
import Util.BeJson;
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
@Path("EmployeeDetails")
public class EmployeeDetailsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmployeeDetailsResource
     */
    public EmployeeDetailsResource() {
    }

    /**
     * Retrieves representation of an instance of rest.EmployeeDetailsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of
     * EmployeeDetailsResource
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
            EmployeeDetailsDAO empDDAO = new EmployeeDetailsDAOImpl();
            String method = json.get("method").toString();
            switch (method) {
                case "set":
                    int eid = empDDAO.setEmployeeDetails(
                            Integer.parseInt(dataJson.get("employee_id").toString()),
                            Integer.parseInt(dataJson.get("genre").toString()),
                            dataJson.get("birthday").toString(),
                            dataJson.get("email").toString(),
                            dataJson.get("address").toString());
                    if (eid != -1) {
                        status = "1";
                    } else {
                        status = "-1";
                        error = "Unknow Error, Insert Data Failed!";
                    }
                    break;
                case "get":
                    int emp_id = Integer.parseInt(dataJson.get("employee_id").toString());
                    JSONObject jsonObject = empDDAO.getEmployeeDetails(emp_id).getJSONObject();
                    jsonArray = new JSONArray();
                    jsonArray.add(jsonObject);
                    status = "1";
                    break;
                case "update":
                    int updateLine = empDDAO.updateEmployeeDetails(
                            Integer.parseInt(dataJson.get("employee_id").toString()),
                            Integer.parseInt(dataJson.get("genre").toString()),
                            dataJson.get("birthday").toString(),
                            dataJson.get("email").toString(),
                            dataJson.get("address").toString());
                    if (updateLine == 1) {
                        status = "1";
                    } else {
                        status = "-1";
                        error = "Update Failed";
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
