package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static io.quarkus.panache.common.Parameters.with;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {
	
	public List<Event> findByParameters(String param){
		return find("upper(name) like upper(?1)", "%"+param+"%").list();
	}
	
}
