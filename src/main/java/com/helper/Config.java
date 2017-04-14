package com.helper;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Администратор on 11.04.2017.
 */
public class Config {

    private static Properties config = new Properties();

    private static InputStream is = Config.class.getResourceAsStream("/config.properties");
    static {
        try{
            config.load(is);
        } catch (Exception e){
            System.out.println("Error reading from property file");
        }
    }
    public static String put() {
        return config.getProperty("PUT");
    }

    public static String get() {
        return config.getProperty("GET");
    }
    public static String post() {
        return config.getProperty("POST");
    }

    public static String conn() {return config.getProperty("CONNECTION");}

    public static String cache() {return config.getProperty("CACHE_CONTROL");}

    public static String user_agent(){
        return config.getProperty("USER_AGENT");
    }
    public static String accept() {
        return config.getProperty("ACCEPT");
    }

    public static String accept_enc() {
        return config.getProperty("ACCEPT_ENCODING");
    }
    public static String accept_lang() {
        return config.getProperty("ACCEPT_LANGUAGE");
    }

    public static String content_length() {return config.getProperty("CONTENT_LENGTH");}

    public static String content_type() {return config.getProperty("CONTENT_TYPE");}

    public static String splitter(){
        return config.getProperty("COLON_SPLITTER");
    }

    public static String new_line(){
        return config.getProperty("NEW_LINE");
    }
    public static String accept_type_xml() {return config.getProperty("ACCEPT_TYPE_XML"); }

    public static String accept_type_json() {return config.getProperty("ACCEPT_TYPE_JSON");}
    public static String server() {
        return config.getProperty("SERVER");
    }

    public static String s_code_200() {return config.getProperty("STATUS_CODE_200_OK");}

    public static String s_code_201() {return config.getProperty("S_CODE_201_CREATED");}

    public static String s_code_400(){
        return config.getProperty("S_CODE_400_BAD_REQUEST");
    }

    public static String s_code_404(){
        return config.getProperty("S_CODE_404_NOT_FOUND");
    }
    public static String accept_encoding() {return config.getProperty("ACCEPT_ENCODING");}

    public static String body(){
        return config.getProperty("BODY");
    }

    public static String host(){return config.getProperty(("HOST"));}
    public static String origin(){return config.getProperty(("ORIGIN"));}
    public static String server_value(){return config.getProperty(("SERVER_VALUE"));}
}
