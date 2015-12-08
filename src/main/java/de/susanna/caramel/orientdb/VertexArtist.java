package de.susanna.caramel.orientdb;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

public interface VertexArtist extends VertexFrame {

    @Property("name")
    public void setName(String name);

    @Property("Name")
    public String getName();
    
    @Property("mbid")
    public void setMbid(String mbid);

    @Property("mbid")
    public String getMbid();
    
    @Property("eid")
    public void setEid(String eid);

    @Property("eid")
    public String getEid();

    @Property("type")
    public void setType(String type);

    @Property("type")
    public String getType();
}