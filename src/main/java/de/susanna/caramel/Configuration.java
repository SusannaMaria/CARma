package de.susanna.caramel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.*;

public class Configuration {
	private static String carma_main_path = null;
	private static String carma_cfg_path = null;
    private static Configuration _instance = null;
    public static final String API_KEY = "apikey";
    private static Logger logger = Logger.getLogger("CARma");
    private Properties props = null;

    private Configuration() {
         props = new Properties();
    	try {
    		
    		carma_main_path =  System.getProperty( "user.home" )+"/.carma";
    		
    		carma_cfg_path =  carma_main_path+"/carma.cfg";
    		
    		File f_carma_cfg_path = new File(carma_cfg_path);
    		
    		if(f_carma_cfg_path.exists() && !f_carma_cfg_path.isDirectory()) { 
    		    InputStream stream = new FileInputStream(carma_cfg_path);
    		                		    
    		    props.load(stream);
    		    stream.close();
    		    System.out.println("Config loaded!");
    		    logger.severe( "Dann mal los." );
    		    logger.warning( "Dann mal los." );
    		}else{
    			
    		}
    	}
    	catch (Exception e) {
    	    // catch Configuration Exception right here
    	}
    }
    

    public synchronized static Configuration getInstance() {
        if (_instance == null){
            _instance = new Configuration();
        }
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
