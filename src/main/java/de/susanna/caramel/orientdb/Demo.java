package de.susanna.caramel.orientdb;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

import de.susanna.caramel.DatabaseHelper;

public class Demo {
	
	public static void main(String[] args) {

		Demo dbh = new Demo();
		
		
	}
	
	
	
	public Demo() {
		super();
		OrientGraphFactory factory = new OrientGraphFactory("plocal:/var/lib/orientdb/susanna").setupPool(1,10);
		OrientGraph graph = factory.getTx();
		
    try {
	        System.out.println("Features = " + graph.getFeatures());
	        System.out.println(graph.countVertices());
	        Iterable<Edge> resultset = graph.getEdges("artist", "Blur");
	        System.out.println(resultset.toString());
	        
            for (Edge edge : resultset) {
            	System.out.println("#"+edge.getProperty("echonest_id"));
            }
	    } finally {
	        graph.shutdown();
	    }
		
	}




}
