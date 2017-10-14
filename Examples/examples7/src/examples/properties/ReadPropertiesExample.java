package examples.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesExample {
	static final String FILENAME = "MyProperties.properties";

	public static void main(String[] args) {
		new ReadPropertiesExample();
	}

	public ReadPropertiesExample() {
		Properties properties = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(FILENAME);
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//properties.list(System.out);
		// This is how you can read name value pairs
		System.out.println("-- using getProperty() --");
		System.out.println("Font=" + properties.getProperty("Font"));
	}
}
