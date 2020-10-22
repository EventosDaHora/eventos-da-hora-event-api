package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.dominio.TicketReserved;
import lombok.extern.java.Log;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/tickets-reserved")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketsReseverdResource {
	
	@GET
	public Response getSectionsByIdEvent(@QueryParam("orderId") Long orderId) {
		
		if (orderId != null) {
			return Response.ok(TicketReserved.findAllTicketReservedByOrderId(orderId)).build();
		} else {
			return Response.ok(TicketReserved.listAll()).build();
		}
	}
	
}
