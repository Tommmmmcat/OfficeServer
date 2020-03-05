package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Util.Config;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hasee
 */
public class DataBaseDAO {

    private Connection conn;
    private static DataBaseDAO instance;
    
    private DataBaseDAO(){
    }
    
    public static DataBaseDAO getInstance() {
        if (instance == null) {
            instance = new DataBaseDAO();
        }
        return instance;
    }

    public Connection connect() throws Exception {
        Class.forName(Config.DRIVE);
        String url = Config.URL+Config.DATABASE_NAME;
        String user = Config.USER;
        String password = Config.PSW;
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
        if (instance != null) {
            instance = null;
        }
    }

}
