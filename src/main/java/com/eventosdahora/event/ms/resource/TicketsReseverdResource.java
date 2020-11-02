package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.service.TicketReservedService;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/tickets-reserved")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketsReseverdResource {
	
	@Inject
	TicketReservedService ticketReservedService;
	
	@GET
	public Response getSectionsByIdEvent(@QueryParam("orderId") Long orderId) {
		
		if (orderId != null) {
			return Response.ok(ticketReservedService.findAllTicketReservedByOrderId(orderId)).build();
		} else {
			return Response.ok(ticketReservedService.findAll()).build();
		}
	}
	
}
