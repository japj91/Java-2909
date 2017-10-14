package A00980851.JDBC;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Name            Japneet Johal A00 980 851
 * Project Name    A00980851Lab7
 * Class  Name     CreatingProperties
 * Date            2017-06-04
 * Creating the properties file
 */
public class CreatingProperties {

   static final String PROPFILENAME= "Prop.properties";

    /**
     * Creating the properites file
     * @throws IOException
     */
   public static void createPropFile() throws IOException{

       Properties properties = new Properties();
       properties.put("db.driver","org.apache.derby.jdbc.EmbeddedDriver");
       properties.put("db.password","admin");
       properties.put("db.user","jap");
       properties.put("db.url","jdbc:derby:derby_jdbc_test;create=true");
       properties.put("db.name","jspweb");
       FileOutputStream fileOutputStream = null;


       fileOutputStream = new FileOutputStream(new File(PROPFILENAME));
       properties.store(fileOutputStream,"Real attempt at A00980851.JDBC");

    }
}
