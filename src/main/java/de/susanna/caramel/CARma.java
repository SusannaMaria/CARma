package de.susanna.caramel;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class CARma {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		System.out.println(Configuration.getInstance().getProperty(
				Configuration.API_KEY));
		
	}

	static {
		final InputStream inputStream = CARma.class.getResourceAsStream("/logging.properties");
		try
		{
		    LogManager.getLogManager().readConfiguration(inputStream);
		}
		catch (final IOException e)
		{
		    Logger.getAnonymousLogger().severe("Could not load default logging.properties file");
		    Logger.getAnonymousLogger().severe(e.getMessage());
		}
	}
	
}
