package de.susanna.caramel;

import com.echonest.api.v4.Artist;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.orientechnologies.orient.core.metadata.schema.OType;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;
import com.tinkerpop.blueprints.impls.orient.OrientVertexType;
import com.tinkerpop.frames.FramedGraph;
import com.tinkerpop.frames.FramedGraphFactory;
import com.tinkerpop.frames.modules.javahandler.JavaHandlerModule;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import de.susanna.caramel.DAO.ItemsDAO;
import de.susanna.caramel.DAO.ItemAttributesDAO;
import de.susanna.caramel.DAO.AlbumsDAO;
import de.susanna.caramel.DAO.AlbumAttributesDAO;
import de.susanna.caramel.model.BeetsArtist;
import de.susanna.caramel.model.Items;
import de.susanna.caramel.model.ItemAttributes;
import de.susanna.caramel.model.Albums;
import de.susanna.caramel.model.AlbumAttributes;
import de.susanna.caramel.orientdb.VertexArtist;

public class DatabaseHelper {

	private EchoNestAPI echoNest = null;
	private LinkedHashSet<BeetsArtist> foreignArtists = null;
	private static final String DB_PATH = "/usr/lib/orientdb/databases/dummy";
	private static final String TAG = DatabaseHelper.class.getSimpleName();
	private static final String DATABASE_NAME = "/home/susanna/beetlib/beet.lib";
	private static final int DATABASE_VERSION = 1;
	private ItemsDAO itemsDAO = null;
	private ItemAttributesDAO itemAttributesDAO = null;
	private AlbumsDAO albumsDAO = null;
	private AlbumAttributesDAO albumAttributesDAO = null;
	private static OrientGraph graph = null;
	private LinkedHashSet<BeetsArtist> artists;
	private FramedGraph<OrientGraph> framedGraph;

	// Relationship relAB = createRelationship( nodeA, nodeC, "length", 2d );

	public DatabaseHelper() {
		super();

		echoNest = new EchoNestAPI(Configuration.getInstance().getProperty(
				Configuration.API_KEY));

		echoNest.setTraceSends(false);

		OrientGraphFactory factory = new OrientGraphFactory(
				"plocal:" + DB_PATH, "admin", "admin");

		OrientGraphNoTx noTx = factory.getNoTx();
		try {
			OrientVertexType artist = noTx.createVertexType("Artist");
			artist.createProperty("name", OType.STRING);
			artist.createProperty("mbid", OType.STRING);
			artist.createProperty("eid", OType.STRING);
			artist.createProperty("type", OType.STRING);

			OrientVertexType notincollArtist = noTx.createVertexType("Outside");
			notincollArtist.addSuperClass(artist);

			noTx.commit();

		} finally {
			noTx.shutdown();
		}
		graph = factory.getTx();

		framedGraph = new FramedGraphFactory(new JavaHandlerModule())
				.create(graph);

		String databaseUrl = "jdbc:sqlite:" + DATABASE_NAME;

		try {
			ConnectionSource connectionSource = new JdbcConnectionSource(
					databaseUrl);

			// instantiate the dao with the connection source

			AlbumsDAO albumDao = new AlbumsDAO(connectionSource);

			this.artists = albumDao.getArtistList();
			processCollection();
			similarArtists();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		graph.shutdown();

	}

	public void processCollection() {
		this.foreignArtists = new LinkedHashSet<BeetsArtist>();

		Iterator it = this.artists.iterator();

		int count = 0;

		while (it.hasNext()) {

			BeetsArtist i = (BeetsArtist) it.next();
			List<Artist> artists;

			try {

				artists = searchArtist(i);

				if (artists.isEmpty()) {
					System.out.println("WTF!:" + i.getArtist() + "not found");
					continue;
				}
				// count++;
				// if (count>10)
				// break;

				Artist echoArtist = artists.get(0);

				i.setArtist(echoArtist);

				System.out.println(count + "#" + echoArtist.getName() + "#"
						+ echoArtist.getID());

				VertexArtist va = framedGraph.addVertex("class:Artist",
						VertexArtist.class);
				va.setName(echoArtist.getName());
				va.setMbid(i.getArtistid());
				va.setEid(echoArtist.getID());
				va.setType("COLLECTION");
				i.setVertexartist(va);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EchoNestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		graph.commit();
	}

	public void similarArtists() {
		Iterator it = this.artists.iterator();
		int count = 0;
		while (it.hasNext()) {
			try {
				BeetsArtist i = (BeetsArtist) it.next();
				Artist artist = i.getArtist();
				if (artist == null) {
					continue;
				}
				System.out.println(i.getName());

				List<Artist> simList = getSimilar(artist);
				int sC = 1;
				for (Artist simArtist : simList) {
					BeetsArtist ba;
					System.out.println(simArtist.getName());

					ba = getCollectionNode(simArtist);

					if (ba == null) {
						ba = getForeignNode(simArtist);
						if (ba == null) {
							String sim_mb_id_str = getForeignMBID(simArtist);
							
							if (sim_mb_id_str != null) {

								ba = new BeetsArtist();

								ba.setName(simArtist.getName());

								ba.setArtistid(sim_mb_id_str);
								VertexArtist vf = framedGraph.addVertex(
										"class:Outside", VertexArtist.class);
								vf.setName(simArtist.getName());
								vf.setMbid(sim_mb_id_str);
								vf.setEid(simArtist.getID());
								vf.setType("FOREIGN");
								ba.setArtist(simArtist);
								ba.setVertexartist(vf);
								this.foreignArtists.add(ba);

							} else {
								System.out.println("Shit!");
							}
						}
					}

					// Create Vertex
					VertexArtist sNode = i.getVertexartist();

					if (sNode.asVertex() == null) {
						System.out.println("->sNode:" + i.getArtist()
								+ "-null!!!");
					} else if (ba != null) {
						VertexArtist tNode = ba.getVertexartist();
						if (tNode != null) {
							Edge simEdge = graph.addEdge(null,
									sNode.asVertex(), tNode.asVertex(),
									"SIMILAR");
							simEdge.setProperty("weight", sC);
							System.out.println("  ---foreign---    "
									+ i.getArtist().getName() + "->"
									+ ba.getArtist().getName());
							sC++;
						}
					}

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (EchoNestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			graph.commit();

		}
	}

	private List<Artist> getSimilar(Artist echoArtist)
			throws InterruptedException {
		boolean bException = true;
		List<Artist> simList;
		int round = 1;
		do {
			try {
				simList = echoArtist.getSimilar(10);
				bException = false;
			} catch (EchoNestException e) {
				System.out.println(e.getMessage());
				System.out.println("Next round:" + round++);
				Thread.sleep(60100);
				simList = null;
			}
		} while (bException);

		return simList;
	}

	private String getForeignMBID(Artist echoArtist)
			throws InterruptedException {

		String sim_mb_id_str;

		boolean bException = true;
		int round = 1;
		do {
			try {
				sim_mb_id_str = echoArtist.getForeignID("musicbrainz");
				bException = false;
			} catch (EchoNestException e) {
				System.out.println(e.getMessage());
				System.out.println("Next round:" + round++);
				Thread.sleep(60100);
				sim_mb_id_str = null;
			}
		} while (bException);

		if (sim_mb_id_str != null) {
			String[] sim_mb_id_arr = sim_mb_id_str.split(":");
			return sim_mb_id_arr[2];
		}

		return null;
	}

	private List<Artist> searchArtist(BeetsArtist i)
			throws InterruptedException {
		List<Artist> artists;
		boolean bException = true;
		int round = 1;
		do {
			try {

				artists = echoNest.searchArtists(i.getName());
				bException = false;
			} catch (EchoNestException e) {

				System.out.println(e.getMessage());
				System.out.println("Next round:" + round++);
				Thread.sleep(60100);
				artists = null;
			}
		} while (bException);

		return artists;
	}

	private BeetsArtist getForeignNode(BeetsArtist foreign) {
		Iterator<BeetsArtist> itr = this.foreignArtists.iterator();
		while (itr.hasNext()) {
			BeetsArtist a = itr.next();
			if (a.getArtistid().equals(foreign.getArtistid())) {

				System.out.println("!f:" + foreign.getArtist());
				return a;
			}
		}
		return null;
	}

	private BeetsArtist getCollectionNode(BeetsArtist own) {
		Iterator<BeetsArtist> itr = this.artists.iterator();
		while (itr.hasNext()) {
			BeetsArtist a = itr.next();
			if (a.getArtistid().equals(own.getArtistid())) {

				System.out.println("!o:" + own.getArtist());
				return a;
			}
		}
		return null;
	}

	private BeetsArtist getCollectionNode(Artist own) throws EchoNestException {
		Iterator<BeetsArtist> itr = this.artists.iterator();
		while (itr.hasNext()) {
			BeetsArtist a = itr.next();

			if (a.getName().equals(own.getName())) {
				System.out.println("!o:" + own.getName());
				return a;
			}

			/*
			 * if ((a.getArtist()==null)||(a.getArtist().getID()==null)){ return
			 * null; }
			 * 
			 * if (a.getArtist()..equals(own.getID())) {
			 * 
			 * System.out.println("!o:" + own.getName()); return a; }
			 */
		}
		return null;
	}

	private BeetsArtist getForeignNode(Artist own) throws EchoNestException {
		Iterator<BeetsArtist> itr = this.foreignArtists.iterator();
		while (itr.hasNext()) {
			BeetsArtist a = itr.next();
			if (a.getArtist().getID().equals(own.getID())) {

				System.out.println("!f:" + own.getName());
				return a;
			}
		}
		return null;
	}

}