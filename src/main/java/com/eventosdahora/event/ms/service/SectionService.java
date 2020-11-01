package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Section;
import com.eventosdahora.event.ms.repository.SectionRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Log
@ApplicationScoped
public class SectionService {
	
	@Inject
	private SectionRepository sectionRepository;
	
	public List<Section> findSectionsByEventId(Long eventId) {
		return sectionRepository.findSectionsByEventId(eventId);
	}
}
