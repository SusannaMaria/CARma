package de.susanna.caramel;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import de.susanna.caramel.DAO.AlbumsDAO;
import de.susanna.caramel.model.Albums;
import de.susanna.caramel.model.BeetsArtist;

public class BeetsHelper {
	
	private static final String DATABASE_NAME = "/home/susanna/beetlib/beet.lib";
	private List<Albums> albumCollection;
	private LinkedHashSet<BeetsArtist> artistCollection;
	
	public void loadBeets(){
		String databaseUrl = "jdbc:sqlite:" + DATABASE_NAME;
	
		try {
			ConnectionSource connectionSource = new JdbcConnectionSource(
					databaseUrl);
	
			// instantiate the dao with the connection source
	
			AlbumsDAO albumDao = new AlbumsDAO(connectionSource);
			this.albumCollection =albumDao.getAlbumsList();
			this.artistCollection = albumDao.getArtistList();
			
			connectionSource.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int getAlbumCount(){
		return this.albumCollection.size();
	}

	public int getArtistCount(){
		return this.artistCollection.size();
	}	
	
}
