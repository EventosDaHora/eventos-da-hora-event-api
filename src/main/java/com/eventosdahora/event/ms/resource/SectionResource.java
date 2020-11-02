package com.eventosdahora.event.ms.resource;

import com.eventosdahora.event.ms.service.SectionService;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Log
@Path("/sections")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SectionResource {

    @Inject
    SectionService service;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Optional.ofNullable(service.findById(id))
                .map(ticket -> Response.ok().entity(ticket).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND.getStatusCode(), "sectionId not found").build());
    }
}
