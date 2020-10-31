package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.TicketReserved;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.List;

import static io.quarkus.panache.common.Parameters.with;

@ApplicationScoped
public class TicketReservedRepository implements PanacheRepository<TicketReserved> {
	private static final String SQL_AVAILABLE
			= " SELECT tr FROM TicketReserved tr"
			  + " JOIN tr.ticket t"
			  + " WHERE t.id = :id"
			  + " AND tr.orderId = :orderId"
			  + " AND tr.confirmed = :isConfirmed"
			  + " AND :hoje <= tr.expirationDate";
	
	public Long findQtdAvailableTickets(Long ticketId, Long orderId) {
		return find(SQL_AVAILABLE, with("id", ticketId)
				                           .and("hoje", LocalDateTime.now())
				                           .and("orderId", orderId)
				                           .and("isConfirmed", false)).count();
	}
	
	public void consolidaCompra(Long ticketId, Long orderId) {
		update("confirmed = true WHERE orderId = :orderId AND ticket.id = :ticketId",
		                      parameters(ticketId, orderId));
		
		update("confirmed = true WHERE orderId = :orderId AND ticket.id = :ticketId",
		                      parameters(ticketId, orderId));
	}
	
	public void resturaTicket(Long ticketId, Long orderId) {
		delete("orderId = :orderId AND ticket.id = :ticketId",
		                      parameters(ticketId, orderId));
	}
	
	private Parameters parameters(Long ticketId, Long orderId) {
		return with("orderId", orderId).and("ticketId", ticketId);
	}
	
	public List<TicketReserved> findAllTicketReservedByOrderId(Long orderId){
		return find("orderId", orderId).list();
	}
}
