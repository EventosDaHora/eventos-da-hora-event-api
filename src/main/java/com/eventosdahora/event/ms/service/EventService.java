package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dominio.Ticket;
import com.eventosdahora.event.ms.dto.EventDTO;
import com.eventosdahora.event.ms.dto.SectionDTO;
import com.eventosdahora.event.ms.repository.EventRepository;
import com.eventosdahora.event.ms.repository.SectionRepository;
import com.eventosdahora.event.ms.repository.TicketRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Log
@ApplicationScoped
public class EventService {

	@Inject
	EventRepository repository;

	@Inject
	SectionService sectionService;

	@Inject
	ImageService imageService;

	public Event getRandomEvent() {
		Random random = new Random();
		int totalEvents = Integer.parseInt(Long.valueOf(repository.count()).toString()) + 1;
		int randomNumber = random.nextInt(totalEvents);
		return repository.findById((long) randomNumber);
	}

	public Optional<Event> findById(final Long eventId) {
		return repository.findByIdOptional(eventId);
	}

	public List<Event> listAll() {
		return repository.listAll();
	}

	public void newEvent(EventDTO eventDTO) {
		Event event = eventDTO.toEntity();

		eventDTO.getSections()
				.stream()
				.map(SectionDTO::toEntity)
				.forEach(event::addSection);

	}
}
