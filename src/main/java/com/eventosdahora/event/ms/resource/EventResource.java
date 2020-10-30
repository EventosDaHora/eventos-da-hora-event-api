package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dominio.Section;
import com.eventosdahora.event.ms.service.EventService;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {
	
	@Inject
	private EventService eventService;
	
	@GET
	public Response getAll() {
		return Response.ok(Event.listAll()).build();
	}
	
	@GET
	@Path("/{eventId}")
	public Response getSectionById(@PathParam Long eventId) {
		Event event = Event.findById(eventId);
		
		if (event != null) {
			return Response.ok(event).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build();
		}
		
	}
	
	@GET
	@Path("/random")
	public Response getRandomEvent() {
		Event event = eventService.getRandomEvent();
		
		if (event != null) {
			return Response.ok(event).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build();
		}
		
	}
	
	@GET
	@Path("/{eventId}/sections")
	public Response getSectionsByIdEvent(@PathParam Long eventId) {
		Optional<Event> optional = Event.findByIdOptional(eventId);
		
		if (optional.isPresent()) {
			return Response.ok(Section.findSectionsByEventId(eventId)).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build();
		}
	}
	
}
