package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.Ticket;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TicketRepository implements PanacheRepository<Ticket> {

    public boolean exist(List<Long> ids) {
       return find("SELECT t FROM Ticket t WHERE t.id IN (:ids)", Parameters.with("ids", ids)).firstResult() != null;
    }
}
