package de.susanna.caramel.DAO;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.List;
import de.susanna.caramel.model.Items;

public class ItemsDAO extends BaseDaoImpl<Items,Integer>{ 
    public ItemsDAO(ConnectionSource connectionSource, Class<Items> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
    public ItemsDAO(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Items.class);
    }
  public List<Items> getItemsList() throws SQLException {
        return this.queryForAll();
    }

}