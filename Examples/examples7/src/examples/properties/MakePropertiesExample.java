package examples.properties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MakePropertiesExample {
	static final String FILENAME = "MyProperties.properties";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("ForegroundColor", "Blue");
		properties.put("BackgroundColor", "Yellow");
		properties.put("Currency", "$US");
		properties.put("Weight", "Lbs");
		properties.put("Font", "Times New Roman");
		properties.put("DateFormat", "mm/dd/yyyy");
	//	properties.list(System.out);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(FILENAME);
			properties.store(out, "My properties file");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
