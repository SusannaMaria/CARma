package de.susanna.caramel.card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import de.susanna.caramel.BeetsHelper;
import de.susanna.caramel.model.Albums;

@XmlRootElement(namespace = "de.susanna.caramel.card")
@XmlType(propOrder = { "name", "date", "recordlist" })
public class CARd_Lib {

	@XmlElementWrapper(name = "recordlist")
	@XmlElement(name = "record")
	private ArrayList<CARd_Record> recordlist = new ArrayList<CARd_Record>();

	private String name;
	private String date;

	public List<CARd_Record> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(ArrayList<CARd_Record> recordlist) {
		this.recordlist = recordlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public CARd_Record getRecord(int i) {
		return recordlist.get(i);
	}

	public void addRecord(CARd_Record rec) {
		recordlist.add(rec);
	}

	public void dump() {
		for (int i = 0; i < recordlist.size(); i++) {
			System.out.println((i + 1) + "|" + recordlist.get(i).toString());
		}
	}

	public boolean getImages(String path) {
		boolean bChanged = false;
		for (int i = 0; i < recordlist.size(); i++) {
			if (recordlist.get(i).loadImage(path)) {
				bChanged = true;
			}
		}
		return bChanged;
	}

	public boolean getRecords() {
		
		boolean bChanged=false;
		
		try {
			
			String url;
			for (int i = 0; i < recordlist.size(); i++) {
				url = recordlist.get(i).getId();
				
				System.out.println(url);
				
				if ((url == null) || (url.isEmpty())) {
					continue;
				}
				

				if (recordlist.get(i).getImage300()!=null){
				
					if ((!recordlist.get(i).getImage300().equals("@unknown")) && (!recordlist.get(i).getImage300().isEmpty())){
						continue;
					}
				}	
				
				if (LoadRecordPage(url,i)){
					bChanged=true;
					
				}
				
				
				System.out.print("... waiting 5s");

				Thread.sleep(5000);

				System.out.println("... done");

			}
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bChanged;
	}

	public boolean LoadRecordPage(String url,int index) {
		int nCount = 0;
		boolean bChange = false;

		Document doc;
		try {
			doc = Jsoup.connect("https://rateyourmusic.com"+url).data("query", "Java").userAgent("Mozilla")
					.cookie("auth", "token").timeout(5000).post();

			if (doc == null) {
				System.err.print("Error!!!!! abort");
				return false;
			}

			String raw = doc.html();
			raw = raw.replace("//eu.rymimg.com", "http://eu.rymimg.com");
			doc = Jsoup.parse(raw, "https://rateyourmusic.com/");

			Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			boolean bFound=false;
			for (Element image : images) {

				String imgclass= image.attr("class");
				
				if ((imgclass==null)||(imgclass.isEmpty())){
					continue;
				}
				if (imgclass.equalsIgnoreCase("coverart_img")){
					getRecord(index).setImage300(image.attr("src"));
//					CARd_Main.logger.info("Image300: "+image.attr("src"));
					bChange=true;
					bFound=true;
					break;
				}
				
			}
			if (!bFound){
				getRecord(index).setImage300("@unknown");
				bChange=true;
			}
			Elements spans = doc.select("span");

			
			for (Element span : spans) {


				String spanclass= span.attr("class");
				String itemprop= span.attr("itemprop");
				
				if ((spanclass==null)||(itemprop==null)){
					continue;
				}
				
				if (spanclass.equalsIgnoreCase("avg_rating")){

					getRecord(index).setRymrating(span.ownText());
//					CARd_Main.logger.info("Rymrating: "+span.ownText());
					bChange=true;
					
				}
				if(itemprop.equalsIgnoreCase("ratingCount")){
					getRecord(index).setRymcount(span.ownText());
//					CARd_Main.logger.info("Rymcount: "+span.ownText());
					bChange=true;
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bChange;
	}

	public boolean getImages300(String path) {
		boolean bChanged = false;
		for (int i = 0; i < recordlist.size(); i++) {
			if (recordlist.get(i).loadImage300(path)) {
				bChanged = true;
			}
		}
		return bChanged;
	}
	
	public int getSize(){
		return recordlist.size();
	}
	
	public void getSimilar(BeetsHelper bh) {
		String record;
		
		for (int i = 0; i < recordlist.size(); i++) {
			
			record = recordlist.get(i).getBand()+"-" + recordlist.get(i).getName();
			
			
			Albums album = bh.getSimilar(record);
			
			if (album.getSimcore()<0.98){
				System.out.println((i + 1) + "|" + record);
				System.out.println("->" +"|"+album.getSimcore()+"|"+album.getId()+"|"+ album.getRecord());				
			}
			

			
		}
	}

}
