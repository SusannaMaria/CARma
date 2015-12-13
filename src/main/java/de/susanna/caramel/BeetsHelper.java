package de.susanna.caramel;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import de.susanna.caramel.DAO.AlbumsDAO;
import de.susanna.caramel.model.Albums;
import de.susanna.caramel.model.BeetsArtist;

public class BeetsHelper {
	
	private static final String DATABASE_NAME = "beet.lib";
	private List<Albums> albumCollection;
	private LinkedHashSet<BeetsArtist> artistCollection;
	
	public void loadBeets(){
		String databaseUrl = "jdbc:sqlite:" + Configuration.carma_main_path+"/"+DATABASE_NAME;
	
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
	
	public void showDiff(){
		
	}
	
	public void dump(){
		
        Iterator<BeetsArtist> itr = artistCollection.iterator();
        while(itr.hasNext()){
        	BeetsArtist ba =  itr.next();
        	
        	
            System.out.println(ba.getName());
            
        }
	
	}

	public void dumpAlbum(){
		
        Iterator<Albums> itr = albumCollection.iterator();
        while(itr.hasNext()){
        	Albums ba =  itr.next();
        	
        	
            System.out.println(ba.getRecord());
            
        }
	
	}	
	
	public Albums getSimilar(String candidate){
		Albums best=null;
		double score;
		double oldscore;
		
        Iterator<Albums> itr = albumCollection.iterator();
        oldscore = 0;
        while(itr.hasNext()){
        	Albums ba =  itr.next();
        	
        	score = StringUtils.getJaroWinklerDistance(candidate, ba.getRecord());
        	
        	if (score>oldscore){
        		best = ba;
        		oldscore = score;
        	}
        }
        best.setSimcore(oldscore);
        return best;
	}

	public void getCoverArt() {

		int count=1;
        Iterator<Albums> itr = albumCollection.iterator();
        while(itr.hasNext()){
        	Albums ba =  itr.next();
        	
        	System.out.println(count+"#"+ba.getImage());
        	count++;
        }	
		
	}	
	
}
