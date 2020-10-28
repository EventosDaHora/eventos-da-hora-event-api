package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Category;
import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dominio.TicketReserved;
import com.eventosdahora.event.ms.dto.OrderDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.kafka.OrderEvent;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log
@ApplicationScoped
public class CategoryService {
	
	public List<Category> getAll(){
		return Category.listAll();
	}
	
}
