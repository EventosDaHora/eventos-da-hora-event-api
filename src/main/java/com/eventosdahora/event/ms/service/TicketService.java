package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.repository.TicketRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TicketService {

    @Inject
    TicketRepository repository;

    public Ticket findById(Long id) {
        return repository.findById(id);
    }
}
