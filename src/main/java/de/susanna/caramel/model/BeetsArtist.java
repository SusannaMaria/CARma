package de.susanna.caramel.model;

import com.echonest.api.v4.Artist;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import de.susanna.caramel.orientdb.VertexArtist;


public class BeetsArtist {

	public static enum Type {MINE, NOTMINE};
		
	private String name=null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private Artist artist=null;
	private String mb_artistid;
	private Type artist_type;

	private VertexArtist vertexartist=null;
		
	public String getMbArtistid() {
		return mb_artistid;
	}
	public void setMbArtistid(String mb_artistid) {
		this.mb_artistid = mb_artistid;
	}
	public Type getArtist_type() {
		return artist_type;
	}
	public void setArtist_type(Type artist_type) {
		this.artist_type = artist_type;
	}


	
	public VertexArtist getVertexartist() {
		return vertexartist;
	}
	public void setVertexartist(VertexArtist vertexartist) {
		this.vertexartist = vertexartist;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public String getArtistid() {
		return mb_artistid;
	}
	public void setArtistid(String mb_artistid) {
		this.mb_artistid = mb_artistid;
	}
	
	@Override
	public boolean equals(Object o){
		  if(o instanceof BeetsArtist){
		    BeetsArtist toCompare = (BeetsArtist) o;
		    return this.mb_artistid.equals(toCompare.mb_artistid);
		  }
		  return false;
		}
	
	@Override
	public int hashCode() {
	    return this.mb_artistid.hashCode();
	}
	
	
}
