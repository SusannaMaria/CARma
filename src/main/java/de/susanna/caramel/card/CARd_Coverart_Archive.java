package de.susanna.caramel.card;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import fm.last.musicbrainz.coverart.CoverArt;
import fm.last.musicbrainz.coverart.CoverArtArchiveClient;
import fm.last.musicbrainz.coverart.CoverArtImage;
import fm.last.musicbrainz.coverart.impl.DefaultCoverArtArchiveClient;

/*
 * https://github.com/lastfm/coverartarchive-api
 */

public class CARd_Coverart_Archive {
	

	public void demo(){
		CoverArtArchiveClient client = new DefaultCoverArtArchiveClient();
		UUID mbid = UUID.fromString("76df3287-6cda-33eb-8e9a-044b5e15ffdd");

		CoverArt coverArt = null;
		try {
		  coverArt = client.getByMbid(mbid);
		  if (coverArt != null) {
		    for (CoverArtImage coverArtImage : coverArt.getImages()) {
		      File output = new File(mbid.toString() + "_" + coverArtImage.getId() + ".jpg");
		      FileUtils.copyInputStreamToFile(coverArtImage.getImage(), output);
		    }
		  }
		} catch (Exception e) {
		  // ...
		}
	}
	
}
