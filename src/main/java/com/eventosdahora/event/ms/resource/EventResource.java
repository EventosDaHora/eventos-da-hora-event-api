package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dto.EventDTO;
import com.eventosdahora.event.ms.service.EventService;
import com.eventosdahora.event.ms.service.SectionService;
import lombok.extern.java.Log;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Optional;

@Log
@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {
	
	@Inject
	EventService eventService;

	@Inject
	SectionService sectionService;
	
	@GET
	public Response getAll(@QueryParam("searchWord") String params, @QueryParam("categoryId") Long idCateogy) {
		return Response.ok(eventService.getAll(params, idCateogy)).build();
	}
	
	@GET
	@Path("/{eventId}")
	public Response getSectionById(@PathParam("eventId") Long eventId) {
		return eventService.findByIdOptional(eventId)
			.map(event -> Response.ok(event).build())
			.orElseGet(() -> Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build());
	}
	
	@GET
	@Path("/random")
	public Response getRandomEvent() {
		return Optional.ofNullable(eventService.getRandomEvent())
				.map(event -> Response.ok(event).build())
				.orElseGet(() -> Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build());
	}
	
	@GET
	@Path("/{eventId}/sections")
	public Response getSectionsByIdEvent(@PathParam Long eventId) {
		return eventService.findByIdOptional(eventId)
				.map(event -> Response.ok(sectionService.findSectionsByEventId(eventId)).build())
				.orElseGet(() -> Response.status(Response.Status.NOT_FOUND.getStatusCode(), "eventId not found").build());
	}

	@POST
	public Response newEvent(EventDTO eventDTO, @Context UriInfo uriInfo) {
		try {
			Event event = eventService.newEvent(eventDTO);
			UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
			uriBuilder.path(event.getId().toString());
			return Response.created(uriBuilder.build())
						   .entity(event)
						   .build();
		} catch (RuntimeException re) {
			return Response.notModified().build();
		}
	}

}
