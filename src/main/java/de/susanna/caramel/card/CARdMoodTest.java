package de.susanna.caramel.card;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;

public class CARdMoodTest {

	private static int width = 1000;
	private static int height = 40;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String path="/RAIDDATA/Music/00_notmine/Portishead/Third/01 - Silence.mood";
		CARdMoodTest cmt = new CARdMoodTest(path);
		
		
	}

	public CARdMoodTest(String mood_file_path) {
		File mood_file = new File(mood_file_path);
		String mood_png_path = FilenameUtils.getFullPath(mood_file_path)
				+ FilenameUtils.getBaseName(mood_file_path) + ".png";

		DataInputStream data_i;
		try {
			data_i = new DataInputStream(new BufferedInputStream(
					new FileInputStream(mood_file)));

			int length = (int) mood_file.length() / 3;
			int[][] moodbar_data = new int[length][3];

			for (int i = 0; i < length; i++) {

				moodbar_data[i][0] = (int) data_i.readByte()& 0xff;
				moodbar_data[i][1] = (int) data_i.readByte()& 0xff;
				moodbar_data[i][2] = (int) data_i.readByte()& 0xff;
			}
			data_i.close();

			BufferedImage img = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);

			Graphics2D g2d = img.createGraphics();

			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, width, height);

			for (int i = 0; i < length; i++) {
				
				Color val = new Color(moodbar_data[i][0], moodbar_data[i][1],
						moodbar_data[i][2]);
				
				g2d.setColor(val);
				g2d.fillRect(i, 0, i, height);
			}

			g2d.dispose();

			File f = new File(mood_png_path);
			try {
				ImageIO.write(img, "png", f);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
