package de.susanna.caramel.model;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import de.susanna.caramel.DAO.AlbumAttributesDAO;
import de.susanna.caramel.DAO.ItemsDAO;


//@DatabaseTable(tableName = "items") 
@DatabaseTable(daoClass = ItemsDAO.class) 
public class Items{ 

	public final static String LYRICS = "lyrics";
	public final static String DISCTITLE = "disctitle";
	public final static String MONTH = "month";
	public final static String CHANNELS = "channels";
	public final static String DISC = "disc";
	public final static String MB_TRACKID = "mb_trackid";
	public final static String COMPOSER = "composer";
	public final static String ALBUMARTIST_SORT = "albumartist_sort";
	public final static String BITDEPTH = "bitdepth";
	public final static String TITLE = "title";
	public final static String MB_ALBUMID = "mb_albumid";
	public final static String ACOUSTID_FINGERPRINT = "acoustid_fingerprint";
	public final static String RG_ALBUM_GAIN = "rg_album_gain";
	public final static String MB_RELEASEGROUPID = "mb_releasegroupid";
	public final static String RG_ALBUM_PEAK = "rg_album_peak";
	public final static String ALBUMARTIST_CREDIT = "albumartist_credit";
	public final static String ACOUSTID_ID = "acoustid_id";
	public final static String FORMAT = "format";
	public final static String ENCODER = "encoder";
	public final static String RG_TRACK_GAIN = "rg_track_gain";
	public final static String DAY = "day";
	public final static String ORIGINAL_YEAR = "original_year";
	public final static String ARTIST = "artist";
	public final static String MB_ALBUMARTISTID = "mb_albumartistid";
	public final static String BPM = "bpm";
	public final static String ARTIST_CREDIT = "artist_credit";
	public final static String GROUPING = "grouping";
	public final static String DISCTOTAL = "disctotal";
	public final static String ALBUM_ID = "album_id";
	public final static String ALBUMSTATUS = "albumstatus";
	public final static String MTIME = "mtime";
	public final static String ORIGINAL_DAY = "original_day";
	public final static String ALBUMARTIST = "albumartist";
	public final static String YEAR = "year";
	public final static String ALBUMDISAMBIG = "albumdisambig";
	public final static String SAMPLERATE = "samplerate";
	public final static String ID = "id";
	public final static String ALBUM = "album";
	public final static String MB_ARTISTID = "mb_artistid";
	public final static String MEDIA = "media";
	public final static String ARTIST_SORT = "artist_sort";
	public final static String COMMENTS = "comments";
	public final static String TRACKTOTAL = "tracktotal";
	public final static String RG_TRACK_PEAK = "rg_track_peak";
	public final static String CATALOGNUM = "catalognum";
	public final static String ADDED = "added";
	public final static String ORIGINAL_MONTH = "original_month";
	public final static String ASIN = "asin";
	public final static String TRACK = "track";
	public final static String COMP = "comp";
	public final static String INITIAL_KEY = "initial_key";
	public final static String GENRE = "genre";
	public final static String PATH = "path";
	public final static String BITRATE = "bitrate";
	public final static String LANGUAGE = "language";
	public final static String COUNTRY = "country";
	public final static String SCRIPT = "script";
	public final static String LABEL = "label";
	public final static String LENGTH = "length";
	public final static String ALBUMTYPE = "albumtype";

	@DatabaseField(dataType = DataType.STRING, columnName =LYRICS)
		String lyrics;
	@DatabaseField(dataType = DataType.STRING, columnName =DISCTITLE)
		String disctitle;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =MONTH)
		Integer month;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =CHANNELS)
		Integer channels;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =DISC)
		Integer disc;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_TRACKID)
		String mb_trackid;
	@DatabaseField(dataType = DataType.STRING, columnName =COMPOSER)
		String composer;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST_SORT)
		String albumartist_sort;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =BITDEPTH)
		Integer bitdepth;
	@DatabaseField(dataType = DataType.STRING, columnName =TITLE)
		String title;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_ALBUMID)
		String mb_albumid;
	@DatabaseField(dataType = DataType.STRING, columnName =ACOUSTID_FINGERPRINT)
		String acoustid_fingerprint;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_ALBUM_GAIN)
		Double rg_album_gain;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_RELEASEGROUPID)
		String mb_releasegroupid;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_ALBUM_PEAK)
		Double rg_album_peak;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST_CREDIT)
		String albumartist_credit;
	@DatabaseField(dataType = DataType.STRING, columnName =ACOUSTID_ID)
		String acoustid_id;
	@DatabaseField(dataType = DataType.STRING, columnName =FORMAT)
		String format;
	@DatabaseField(dataType = DataType.STRING, columnName =ENCODER)
		String encoder;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_TRACK_GAIN)
		Double rg_track_gain;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =DAY)
		Integer day;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_YEAR)
		Integer original_year;
	@DatabaseField(dataType = DataType.STRING, columnName =ARTIST)
		String artist;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_ALBUMARTISTID)
		String mb_albumartistid;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =BPM)
		Integer bpm;
	@DatabaseField(dataType = DataType.STRING, columnName =ARTIST_CREDIT)
		String artist_credit;
	@DatabaseField(dataType = DataType.STRING, columnName =GROUPING)
		String grouping;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =DISCTOTAL)
		Integer disctotal;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ALBUM_ID)
		Integer album_id;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMSTATUS)
		String albumstatus;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =MTIME)
		Double mtime;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_DAY)
		Integer original_day;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMARTIST)
		String albumartist;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =YEAR)
		Integer year;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMDISAMBIG)
		String albumdisambig;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =SAMPLERATE)
		Integer samplerate;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ID, id = true)
		Integer id;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUM)
		String album;
	@DatabaseField(dataType = DataType.STRING, columnName =MB_ARTISTID)
		String mb_artistid;
	@DatabaseField(dataType = DataType.STRING, columnName =MEDIA)
		String media;
	@DatabaseField(dataType = DataType.STRING, columnName =ARTIST_SORT)
		String artist_sort;
	@DatabaseField(dataType = DataType.STRING, columnName =COMMENTS)
		String comments;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =TRACKTOTAL)
		Integer tracktotal;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =RG_TRACK_PEAK)
		Double rg_track_peak;
	@DatabaseField(dataType = DataType.STRING, columnName =CATALOGNUM)
		String catalognum;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =ADDED)
		Double added;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ORIGINAL_MONTH)
		Integer original_month;
	@DatabaseField(dataType = DataType.STRING, columnName =ASIN)
		String asin;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =TRACK)
		Integer track;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =COMP)
		Integer comp;
	@DatabaseField(dataType = DataType.STRING, columnName =INITIAL_KEY)
		String initial_key;
	@DatabaseField(dataType = DataType.STRING, columnName =GENRE)
		String genre;
	@DatabaseField(dataType = DataType.BYTE_ARRAY, columnName =PATH)
		byte[] path;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =BITRATE)
		Integer bitrate;
	@DatabaseField(dataType = DataType.STRING, columnName =LANGUAGE)
		String language;
	@DatabaseField(dataType = DataType.STRING, columnName =COUNTRY)
		String country;
	@DatabaseField(dataType = DataType.STRING, columnName =SCRIPT)
		String script;
	@DatabaseField(dataType = DataType.STRING, columnName =LABEL)
		String label;
	@DatabaseField(dataType = DataType.DOUBLE_OBJ, columnName =LENGTH)
		Double length;
	@DatabaseField(dataType = DataType.STRING, columnName =ALBUMTYPE)
		String albumtype;

}