package br.com.zaul.business.persistence.manager;

import java.util.List;

public interface DatabaseManager {

	void createTable(String name);
	
	void insert(String tableName, String value);
	
	List<Object> findAll(String tableName);
}
