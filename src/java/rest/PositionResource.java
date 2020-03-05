/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DAO.DepartmentDAO;
import DAO.DepartmentDAOImpl;
import DAO.PositionDAO;
import DAO.PositionDAOImpl;
import DTO.Department;
import DTO.Position;
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
@Path("Position")
public class PositionResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PositionResource
     */
    public PositionResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PositionResource
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
     * PUT method for updating or creating an instance of PositionResource
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
            String method = json.get("method").toString();
            JSONObject dataJson = (JSONObject) json.get("data");
            PositionDAO posDao = new PositionDAOImpl();
            switch (method) {
                case "one_position":
                    int position_id = Integer.parseInt(dataJson.get("position_id").toString());
                    JSONObject posJson_one = posDao.getPositionById(position_id).getJSONObject();
                    jsonArray.add(posJson_one);
                    status = "1";
                    break;
                case "multiple_positions":
                    int department_id = Integer.parseInt(dataJson.get("department_id").toString());
                    List<Position> posList = posDao.getAllPositionByDeptId(department_id);
                    for (Position pos : posList) {
                        JSONObject posJson_mul = pos.getJSONObject();
                        jsonArray.add(posJson_mul);
                    }
                    status = "1";
                    break;
                default:
                    status = "-1";
                    error = "NO METHOD HAS BEEN CHOOSED!";
                    break;
            }
        } catch (Exception ex) {
            status = "-1";
            error = ex.toString();
            Logger.getLogger(EmployeeResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return BeJson.getJson(status, error, jsonArray).toJSONString();
    }
}
