package br.com.zaul.business.persistence.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Mappings;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.KeyValue;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.RootClass;
import org.hibernate.mapping.SimpleValue;
import org.hibernate.mapping.Table;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class HibernateDatabaseManager implements DatabaseManager {

	@Inject
	private Configuration configuration;
	
	@Inject
	private Session session;
	
	@Override
	public void createTable(String name) {
		Mappings mappings = configuration.createMappings();
		
		Table table = mappings.addTable(null, null, name, null, false);
		Column column = new Column();
		column.setName("value");
		//column.setSqlTypeCode(1);
		column.setSqlType("VARCHAR(256)");

		table.addColumn(column);
		mappings.addColumnBinding("value", column, table);

		
		RootClass rootClass = new RootClass();
		rootClass.setEntityName(name);
		rootClass.setJpaEntityName(name);
		
		Property property = new Property();
		property.setName("value");
		
		rootClass.addProperty(property);
		
		KeyValue keyValue = new SimpleValue(mappings);
		rootClass.setIdentifier(keyValue);
		
		mappings.addClass(rootClass);
		
		new SchemaExport(configuration).create(false, true);
		
		System.out.println(name);
		
	}

	@Override
	public void insert(String tableName, String value) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("value", value);

		session.beginTransaction();
		session.save(tableName, param);
		session.flush();
		session.close();
	}

	@Override
	public List<Object> findAll(String tableName) {
		Query query = session.createQuery("FROM " + tableName);
		
		return Collections.singletonList(query.uniqueResult());
	}

}
