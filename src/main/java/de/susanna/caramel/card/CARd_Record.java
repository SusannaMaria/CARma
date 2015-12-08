package de.susanna.caramel.card;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "record")
public class CARd_Record {
	String image;
	String image300;
	
	public String getImage300() {
		return image300;
	}

	public void setImage300(String image300) {
		this.image300 = image300;
	}

	String band;
	String name;
	String id_band;
	String date;
	String ownership;
	String year;
	String rymrating;
	String rymcount;
	
	public String getRymrating() {
		return rymrating;
	}

	public void setRymrating(String rymrating) {
		this.rymrating = rymrating;
	}

	public String getRymcount() {
		return rymcount;
	}

	public void setRymcount(String rymcount) {
		this.rymcount= rymcount;
	}

	public String getId_band() {
		return id_band;
	}

	public void setId_band(String id_band) {
		this.id_band = id_band;
	}

	String id;
	String rating;
	String genre;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
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

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String toString() {
		return image + "|" + image300 + "|" + date + "|" + rating + "|" + band + "|" + name
				+ "|" + year + "|" + genre + "|" + ownership + "|" + id + "|"
				+ id_band;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public boolean checkFile(String path){
		
		
		
		  File f = new File(path);
		 
		  if(f.exists()){
			  return true;
		  }else{
			  return false;
		  }
	}
	
	public boolean loadImage(String path) {

		boolean bChanged=false;
		if ((image==null)||(image.length()==0)){
			image = createImageName(path);
			//System.exit(1);
			bChanged=true;
		}
		
		try {

			String[] parts = 		image.split("/");
			
			
			if (checkFile(path+"/"+parts[parts.length-1])){
				return bChanged;
			}
			if (parts[parts.length-1].startsWith("card")){
				CARd_Main.logger.info("Create: "+image);
				createImage(150,image);
				return bChanged;
			}else if(parts[parts.length-1].startsWith("blockeds")){
				image = createImageName(path);
				createImage(150,image);
				CARd_Main.logger.info("Create: "+image);
				bChanged=true;
				
				return bChanged;
			}
			
			URL url;
			url = new URL(image);
				
			CARd_Main.logger.info("Load: "+image);
			
			InputStream in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(path+"/"+parts[parts.length-1]));
					

			for (int i; (i = in.read()) != -1;) {
				out.write(i);
			}
			in.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bChanged;

	}
	
	public boolean loadImage300(String path) {

		boolean bChanged=false;
		if ((image300==null)||(image300.length()==0)||(image300.startsWith("@unknown"))){
			image300 = createImageName(path);
			//System.exit(1);
			bChanged=true;
			//return bChanged;
		}
		
		try {

			String[] parts = 		image300.split("/");
			
			
			if (checkFile(path+"/"+parts[parts.length-1])){
				return bChanged;
			}
			if (parts[parts.length-1].startsWith("card")){
				CARd_Main.logger.info("Create: "+image300);
				createImage(300,image300);
				return bChanged;
			}else if(parts[parts.length-1].startsWith("blockeds")){
				image = createImageName(path);
				createImage(300,image300);
				CARd_Main.logger.info("Create: "+image300);
				bChanged=true;
				
				return bChanged;
			}
			
			URL url;
			url = new URL(image300);
				
			CARd_Main.logger.info("Load: "+image300);
			
			InputStream in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(path+"/"+parts[parts.length-1]));
					

			for (int i; (i = in.read()) != -1;) {
				out.write(i);
			}
			in.close();
			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bChanged;

	}
	
	public String createImageName(String path){
		String filename = path+"/card_"+UUID.randomUUID()+".png";
		return filename;
		
	}
	
	public void createImage(int size,String fp){
        BufferedImage img = new BufferedImage(
                size, size, BufferedImage.TYPE_INT_RGB);
        int h = size/15;
            Graphics2D g2d = img.createGraphics();
        g2d.setColor(Color.WHITE);  
        g2d.fillRect(0, 0, size, size);  
            g2d.setPaint(Color.BLACK);
            g2d.setFont(new Font("Serif", Font.BOLD, h));
           
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(this.band, 10, h);
            g2d.drawString(this.name, 10, h*4);
            g2d.dispose();
            
            File f = new File(fp);
            try {
				ImageIO.write(img, "png", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	
}
