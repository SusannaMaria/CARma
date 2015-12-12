package de.susanna.caramel.card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
//import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class CARd_Loader {

	CARd_Lib mylib;
	public CARd_Lib getMylib() {
		return mylib;
	}

	public void setMylib(CARd_Lib mylib) {
		this.mylib = mylib;
	}
	private static final String LIBRARY_XML = "rym.xml";

	private static String userpath;
	
	public static String getUserpath() {
		return userpath;
	}

	public static void setUserpath(String userpath) {
		CARd_Loader.userpath = userpath;
	}

	public void LoadFromFile(String filepath) {
		try {
			File input = new File(filepath);
			Document doc = Jsoup.parse(input, "UTF-8");
			LoadMain(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean LoadFromURL(String url,int type) {
		try {
			int  result=0; 
			
			Document doc = Jsoup.connect(url)
					  .data("query", "Java")
					  .userAgent("Mozilla")
					  .cookie("auth", "token")
					  .timeout(3000)
					  .post();
			if (doc==null){
				System.err.print("Error!!!!! abort");
				return false;
			}
			
			switch(type){
				case 0:
					result=LoadMain(doc);
					break;
				default:
					break;
			
			}
			
			
//			CARd_Main.logger.info("Processing: "+url+" entries:"+result);
			if (result<1){
				return false;
			}else{
				return true;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	

	
	public int LoadMain(Document doc) {

		int nCount=0;
		String raw = doc.html();
		raw = raw.replace("//eu.rymimg.com", "http://eu.rymimg.com");
//		Whitelist mylist = new Whitelist();
//		mylist.preserveRelativeLinks(true);
//		mylist.addAttributes("a", "href", "class");
//		String safe = Jsoup.clean(raw, mylist.relaxed());

		doc = Jsoup.parse(raw, "https://rateyourmusic.com/");
		Element table = doc.select("table").get(1);
		Elements trows = table.select("tr");

		for (Element trow : trows) {

			Elements tcells = trow.select("td");

			if (tcells.size() != 6) {
				continue;
			}

			CARd_Record entry = new CARd_Record();

			Elements img_1 = tcells.get(0).select("img");
			if ((img_1 == null) || (img_1.size() == 0)) {
				entry.setImage("");
			} else {
				entry.setImage(img_1.get(0).absUrl("src"));
			}

			String td_2 = tcells.get(1).text();
			entry.setDate(td_2);

			Elements img_2 = tcells.get(2).select("img");
			if ((img_2 == null) || (img_2.size() == 0)) {
				entry.setRating("");
			} else {
				entry.setRating(img_2.get(0).attr("alt"));
			}

			Elements td_3 = tcells.get(3).select("a");
			entry.setBand(td_3.get(0).ownText());
			entry.setId_band(td_3.get(0).attr("href"));
			entry.setName(td_3.get(1).ownText());
			entry.setId(td_3.get(1).attr("href"));

			String genre = "";
			for (int i = 2; i < td_3.size(); i++) {
				if (genre.length() > 0) {
					genre += "#";
				}
				genre += td_3.get(i).ownText();
			}
			entry.setGenre(genre);
			// System.out.println(tcells.get(3).html());
			Elements td_3y = tcells.get(3).select("span");
			if ((td_3y == null) || (td_3y.size() == 0)) {
				entry.setYear("");
			} else {

				if (td_3y.get(0).ownText().length() == 6) {
					entry.setYear(td_3y.get(0).ownText().substring(1, 5));
				} else {
					entry.setYear("");
				}
			}

			String td_4 = tcells.get(4).text();
			entry.setOwnership(td_4);

			mylib.addRecord(entry);
			nCount++;

		}
		return nCount;
	}

	public void dump() {
		mylib.dump();

	}

	public void initLib(String name, String date, String userpath) {
		mylib = new CARd_Lib();
		mylib.setDate(date);
		mylib.setName(name);
		this.userpath = userpath;

	}

	public void writeXML() {
		// create JAXB context and instantiate marshaller

		try {
			JAXBContext context;
			context = JAXBContext.newInstance(CARd_Lib.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Write to System.out
			//m.marshal(mylib, System.out);

			// Write to File
			m.marshal(mylib, new File(this.userpath+"/"+LIBRARY_XML));
//			CARd_Main.logger.info("Write: "+this.userpath+"/"+LIBRARY_XML);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean checkXML(String path){
		
		
		
		  File f = new File(path+"/"+LIBRARY_XML);
		 
		  if(f.exists()){
			  return true;
		  }else{
			  return false;
		  }
	}
	
	
	public void readXML(String path){
		

		
		if (!checkXML(path)){
			return;
		}
		
		if (this.userpath==null){
			this.userpath = path;
		}
		
	    // create JAXB context and instantiate marshaller
	    JAXBContext context;
		try {
			context = JAXBContext.newInstance(CARd_Lib.class);
		    Unmarshaller um = context.createUnmarshaller();
		    mylib = (CARd_Lib) um.unmarshal(new FileReader(path+"/"+LIBRARY_XML));
		    
		    
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getImgs() {
		mylib.getImages300(this.userpath+"/cover300");
		
	}
	
	public void loadRecords(){
		if (mylib.getRecords()){
			writeXML();
		}
	}
	public int getRymBands(){
		return mylib.getSize();
	}


}
