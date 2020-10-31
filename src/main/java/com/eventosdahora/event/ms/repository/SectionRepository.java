package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.Section;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SectionRepository implements PanacheRepository<Section> {
	
	public List<Section> findSectionsByEventId(Long eventId) {
		return find("event.id", eventId).list();
	}
}
