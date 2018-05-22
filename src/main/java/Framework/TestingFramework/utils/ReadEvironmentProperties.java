package Framework.TestingFramework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadEvironmentProperties {
	
	public static Map<String, String> getEnvironmentProperties() {
		Properties prop = new Properties();
		Map<String, String> propMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			String filePATH = "src//main//java//Framework//TestingFramework//config//Env.properties";
			fis = new FileInputStream(filePATH);
			prop.load(fis);
			for (String key : prop.stringPropertyNames()) {
				propMap.put(key, prop.getProperty(key));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return Collections.unmodifiableMap(propMap);
	}
}
