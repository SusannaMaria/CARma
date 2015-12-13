package de.susanna.caramel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class CoverCleaner {
	int withCover = 0;
	int noCover = 0;

	public void listDirectory(String dirPath) {

		File dir = new File(dirPath);

		File[] firstLevelFiles = dir.listFiles();

		if (firstLevelFiles != null && firstLevelFiles.length > 0) {

			ArrayList<File> coverList = new ArrayList<File>();

			for (File aFile : firstLevelFiles) {

				if (aFile.isDirectory()) {
					System.out.println("[" + aFile.getName() + "]");
					listDirectory(aFile.getAbsolutePath());
				} else {

					try {

						String type = getContentType(aFile);
						if (type != null && type.contains("image")) {
							System.out.println(aFile.getName() + "#"
									+ getContentType(aFile));

							coverList.add(aFile);

						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (coverList.isEmpty()) {
				noCover++;
			} else if (coverList.size() == 1) {
				withCover++;
				System.out.println(coverList.get(0).getParent()+"#"+coverList.get(0).getName());
				
				String fn = coverList.get(0).getName();
				
			
				String[] fna = fn.split("\\.");
			
				if (!fn.equalsIgnoreCase("cover."+fna[fna.length-1])){
					File newFile= new File(coverList.get(0).getParent(),"cover."+fna[fna.length-1]);
					System.out.println("Rename:"+newFile.getAbsolutePath());
					coverList.get(0).renameTo(newFile);
				}
				
				
				
			} else {
				int bigIndex = -1;
				long bigSize = 0;
				if (coverList.size() > 1) {
					for (int i = 0; i < coverList.size(); i++) {
						try {
							if (bigSize < Files.size(coverList.get(i).toPath())) {
								bigIndex = i;
								bigSize = Files.size(coverList.get(i).toPath());
							}
							System.out.println();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (bigIndex!=-1){
						for (int i = 0; i < coverList.size(); i++) {
							if (i!=bigIndex){
								System.out.println("delete: "+coverList.get(i).getAbsolutePath());
								//coverList.get(i).delete();
							}
						}						
					}

				}
			}
			coverList = null;

		}
	}

	public String getContentType(File file) throws IOException {
		return Files.probeContentType(file.toPath());
	}

	public static void main(String[] args) {
		CoverCleaner test = new CoverCleaner();
		String dirToList = "/opt/Music/complete";
		test.listDirectory(dirToList);
	}

}
