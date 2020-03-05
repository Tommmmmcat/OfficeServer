/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.security.MessageDigest;

/**
 *
 * @author QAQ
 */
public class SecurityMD5 {

    public final static String SLAT = "&%5123***&&%%$$#@";

    public static String encrypt_md5(String dataStr) {
        try {
            dataStr = dataStr + SLAT;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

}
