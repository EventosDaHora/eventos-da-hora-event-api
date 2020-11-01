package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.Ticket;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {

}
