/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author QAQ
 */
public class Utils {

    public static String replceSymbol(String str) {
        str = str.replace(" ", "").replace("+", "").replace("-", "").replace("*", "").replace("/", "").replace("\"", "").replace("\'", "");
        return str;
    }

}
