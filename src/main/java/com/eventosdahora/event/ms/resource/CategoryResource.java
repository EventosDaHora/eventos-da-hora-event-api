package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dominio.Section;
import com.eventosdahora.event.ms.service.CategoryService;
import lombok.extern.java.Log;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Log
@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {
	
	@Inject
	public CategoryService categoryService;
	
	@GET
	public Response getAll() {
		return Response.ok(categoryService.getAll()).build();
	}
	
	
}
