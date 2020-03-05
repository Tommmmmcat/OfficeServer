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
public class Position {

    private int position_id;
    private String position_name;

    public Position() {
    }

    public Position(int position_id, String position_name) {
        this.position_id = position_id;
        this.position_name = position_name;
    }

    public int getPositionId() {
        return position_id;
    }

    public void setPositionId(int position_id) {
        this.position_id = position_id;
    }

    public String getPositionName() {
        return position_name;
    }

    public void setPositionName(String position_name) {
        this.position_name = position_name;
    }

    public JSONObject getJSONObject() {
        JSONObject json = new JSONObject();
        json.put("position_id", position_id);
        json.put("position_name", position_name);
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
        final Position other = (Position) obj;
        if (!Objects.equals(this.position_id, other.position_id)) {
            return false;
        }
        return true;
    }

}
