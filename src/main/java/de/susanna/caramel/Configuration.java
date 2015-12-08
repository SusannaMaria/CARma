package de.susanna.caramel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Configuration _instance = null;
    public static final String API_KEY = "apikey";
    		
    private Properties props = null;

    private Configuration() {
         props = new Properties();
    	try {
	    //FileInputStream fis = new FileInputStream(new File("echonest.properties"));
	    InputStream stream =
	            this.getClass().getResourceAsStream("echonest.properties");
	    
	    props.load(stream);
	    stream.close();
    	}
    	catch (Exception e) {
    	    // catch Configuration Exception right here
    	}
    }

    public synchronized static Configuration getInstance() {
        if (_instance == null)
            _instance = new Configuration();
        return _instance;
    }

    // get property value by name
    public String getProperty(String key) {
        String value = null;
        if (props.containsKey(key))
            value = (String) props.get(key);
        else {
            // the property is absent
        }
        return value;
    }
}
