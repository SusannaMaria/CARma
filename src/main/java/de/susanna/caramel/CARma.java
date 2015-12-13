package de.susanna.caramel;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import de.susanna.caramel.card.CARd_Lib;
import de.susanna.caramel.card.CARd_Loader;
import de.susanna.caramel.card.CARd_Main;

public class CARma {
	
	public CARma() {
		super();
		loadProject();
		
	
	}
	CARd_Lib cl = null;
	CARd_Main cm =null;
	BeetsHelper bh = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		CARma carma = new CARma();

		
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
	
	public void loadProject(){
		CARd_Main cm = new CARd_Main();
		
		cm.readrym(Configuration.getInstance().getProperty(Configuration.RYMUSER),
				Configuration.carma_main_path);
		
		cl= cm.getCl().getMylib();
	
		bh = new BeetsHelper();
		bh.loadBeets();
		//cl.getSimilar(bh);

		bh.getCoverArt();
	}


	
	
	public void dump() {
		
		cl.dump();
		bh.dumpAlbum();
	}
	
}
