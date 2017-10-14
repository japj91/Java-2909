package Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    examples7
 * Class  Name     createProperites
 * Date            2017-06-03
 */
public class createProperites {
    static String Filename = "dbb.properties";

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        properties.put("db.driver","org.apache.derby.jdbc.EmbeddedDriver");
        properties.put("db.url","jdbc:derby:derby_jdbc_test;create=true");
        properties.put("db.user","johnSnow");
        properties.put("db.password","admin");
        properties.put("db.name","jspweb");

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Filename);
            properties.store(out,"My First properties file :) ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (out != null){
                out.close();
            }
        }
    }
}