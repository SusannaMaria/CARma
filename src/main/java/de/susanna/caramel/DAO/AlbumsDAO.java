package de.susanna.caramel.DAO;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import de.susanna.caramel.model.Albums;
import de.susanna.caramel.model.BeetsArtist;

public class AlbumsDAO extends BaseDaoImpl<Albums,Integer>{ 
    public AlbumsDAO(ConnectionSource connectionSource, Class<Albums> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    
    // this constructor must be defined
    public AlbumsDAO(ConnectionSource connectionSource)
      throws SQLException {
        super(connectionSource,Albums.class);
    }
    
    
  public List<Albums> getAlbumsList() throws SQLException {
        return this.queryForAll();
    }

  public LinkedHashSet<BeetsArtist> getArtistList() throws SQLException {
	  
	 List<Albums> albums = getAlbumsList();
	 LinkedHashSet<BeetsArtist> artists = new LinkedHashSet<BeetsArtist>();  
	 
	 for ( Iterator<Albums> iterator = albums.iterator(); iterator.hasNext(); ){
		 Albums album = iterator.next();
		 artists.add(album.getArtist());
		 
	 }
	 return artists;
	 
	 
	  
  }
  
}