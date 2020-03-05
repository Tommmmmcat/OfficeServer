/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author QAQ
 */
public class BeJson {

    public static JSONObject getJson(String status, String error, JSONArray jsonArray) {
        JSONObject json = new JSONObject();
        json.put("status", status);
        json.put("error", error);
        json.put("data", jsonArray);
        return json;
    }

}
