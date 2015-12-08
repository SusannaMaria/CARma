package de.susanna.caramel.DAO;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import de.susanna.caramel.model.ItemAttributes;

public class ItemAttributesDAO extends BaseDaoImpl<ItemAttributes,Integer>{ 
    public ItemAttributesDAO(ConnectionSource connectionSource, Class<ItemAttributes> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ItemAttributesDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, ItemAttributes.class);
    }    
    
  public List<ItemAttributes> getItemAttributesList() throws SQLException {
        return this.queryForAll();
    }

}