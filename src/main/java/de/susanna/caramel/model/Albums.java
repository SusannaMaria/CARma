package de.susanna.caramel.model;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import de.susanna.caramel.DAO.AlbumsDAO;


//@DatabaseTable(tableName = "albums") 
@DatabaseTable(daoClass = AlbumsDAO.class) 
public class Albums { 
	private double simcore = 0.0;
	public double getSimcore() {
		return simcore;
	}

	public void setSimcore(double simcore) {
		this.simcore = simcore;
	}
	public final static String DISCTOTAL = "disctotal";
	public final static String ALBUMSTATUS = "albumstatus";
	public final static String MONTH = "month";
	public final static String ORIGINAL_DAY = "original_day";
	public final static String ALBUMARTIST = "albumartist";
	public final static String YEAR = "year";
	public final static String ALBUMDISAMBIG = "albumdisambig";
	public final static String ALBUMARTIST_SORT = "albumartist_sort";
	public final static String ID = "id";
	public final static String ALBUM = "album";
	public final static String ASIN = "asin";
	public final static String SCRIPT = "script";
	public final static String MB_ALBUMID = "mb_albumid";
	public final static String LABEL = "label";
	public final static String RG_ALBUM_GAIN = "rg_album_gain";
	public final static String MB_RELEASEGROUPID = "mb_releasegroupid";
	public final static String ARTPATH = "artpath";
	public final static String RG_ALBUM_PEAK = "rg_album_peak";
	public final static String ALBUMARTIST_CREDIT = "albumartist_credit";
	public final static String CATALOGNUM = "catalognum";
	public final static String ADDED = "added";
	public final static String ORIGINAL_MONTH = "original_month";
	public final static String COMP = "comp";
	public final static String GENRE = "genre";
	public final static String DAY = "day";
	public final static String ORIGINAL_YEAR = "original_year";
	public final static String LANGUAGE = "language";
	public final static String MB_ALBUMARTISTID = "mb_albumartistid";
	public final static String COUNTRY = "country";
	public final static String ALBUMTYPE = "albumtype";

	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =DISCTOTAL)
		Integer disctotal;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMSTATUS)
		String albumstatus;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =MONTH)
		Integer month;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_DAY)
		Integer original_day;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST)
		String albumartist;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =YEAR)
		Integer year;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMDISAMBIG)
		String albumdisambig;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST_SORT)
		String albumartist_sort;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ID, id = true)
		Integer id;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUM)
		String album;
	@DatabaseField(dataType = DataType.STRING, columnName =ASIN)
		String asin;
	@DatabaseField(dataType = DataType.STRING, columnName =SCRIPT)
		String script;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_ALBUMID)
		String mb_albumid;
	@DatabaseField(dataType = DataType.STRING, columnName =LABEL)
		String label;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_ALBUM_GAIN)
		Double rg_album_gain;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_RELEASEGROUPID)
		String mb_releasegroupid;
	@DatabaseField(dataType = DataType.BYTE_ARRAY, columnName =ARTPATH)
		byte[] artpath;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_ALBUM_PEAK)
		Double rg_album_peak;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST_CREDIT)
		String albumartist_credit;
	@DatabaseField(dataType = DataType.STRING, columnName =CATALOGNUM)
		String catalognum;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =ADDED)
		Double added;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_MONTH)
		Integer original_month;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =COMP)
		Integer comp;
	@DatabaseField(dataType = DataType.STRING, columnName =GENRE)
		String genre;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =DAY)
		Integer day;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_YEAR)
		Integer original_year;
	@DatabaseField(dataType = DataType.STRING, columnName =LANGUAGE)
		String language;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_ALBUMARTISTID)
		String mb_albumartistid;
	@DatabaseField(dataType = DataType.STRING, columnName =COUNTRY)
		String country;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMTYPE)
		String albumtype;

	public BeetsArtist getArtist(){
		BeetsArtist result = new BeetsArtist();
		result.setName(albumartist);
		result.setArtistid(mb_albumartistid);
		return result;
	}

	public String toString(){
		return album;
		
	}
	
	public String getRecord(){
		return albumartist+"-"+album;
	}
	public int getId(){
		return id;
	}	
	
	
}