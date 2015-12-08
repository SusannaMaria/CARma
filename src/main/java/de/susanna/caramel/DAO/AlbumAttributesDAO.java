package de.susanna.caramel.DAO;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import de.susanna.caramel.model.AlbumAttributes;

public class AlbumAttributesDAO extends BaseDaoImpl<AlbumAttributes,Integer>{ 
    public AlbumAttributesDAO(ConnectionSource connectionSource, Class<AlbumAttributes> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    public AlbumAttributesDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, AlbumAttributes.class);
    }
  public List<AlbumAttributes> getAlbumAttributesList() throws SQLException {
        return this.queryForAll();
    }

}