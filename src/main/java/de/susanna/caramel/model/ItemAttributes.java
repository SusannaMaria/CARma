package de.susanna.caramel.model;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import de.susanna.caramel.DAO.ItemAttributesDAO;


//@DatabaseTable(tableName = "item_attributes") 
@DatabaseTable(daoClass = ItemAttributesDAO.class) 
public class ItemAttributes { 

	public final static String ID = "id";
	public final static String ENTITY_ID = "entity_id";
	public final static String KEY = "key";
	public final static String VALUE = "value";

	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ID, id = true)
		Integer id;
	@DatabaseField(dataType = DataType.INTEGER_OBJ, columnName =ENTITY_ID)
		Integer entity_id;
	@DatabaseField(dataType = DataType.STRING, columnName =KEY)
		String key;
	@DatabaseField(dataType = DataType.STRING, columnName =VALUE)
		String value;

}