package br.com.zaul.service.rest.impl;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.com.zaul.business.persistence.manager.DatabaseManager;

@Path("/model")
public class DynamicModelService {
	
	@Inject
	private DatabaseManager databaseManager;

	@GET
	@Path("/create/{tableName}")
	public Response createTable(@PathParam("tableName") String tableName) {
		databaseManager.createTable(tableName);		
		return Response.ok("Table created successfully").build();
	}
	
	@GET
	@Path("/insert/{tableName}/{value}")
	public Response insert(@PathParam("tableName") String tableName, @PathParam("value") String value) {
		databaseManager.insert(tableName, value);	
		return Response.ok("Inserted with success").build();
	}
	
	@GET
	@Path("/{tableName}")
	public Response findAll(@PathParam("tableName") String tableName) {
		List<Object> allElements = databaseManager.findAll(tableName);
		return Response.ok(allElements).build();
	}
	
}
