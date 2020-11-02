package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dominio.Section;
import com.eventosdahora.event.ms.dto.EventDTO;
import com.eventosdahora.event.ms.dto.SectionDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.repository.EventRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Log
@ApplicationScoped
public class EventService extends GenericService<Event> {

    @Inject
    EventRepository repository;

    @Inject
    CategoryService categoryService;

    @Inject
    SectionService sectionService;

    @Inject
    ImageService imageService;

    public Optional<Event> getRandomEvent() {
        Random random = new Random();
        int totalEvents = Integer.parseInt(Long.valueOf(repository.count()).toString()) + 1;
        int randomNumber = random.nextInt(totalEvents);
        return repository.findByIdOptional((long) randomNumber);
    }

    @Transactional
    public Event newEvent(EventDTO eventDTO) {
        Event event = eventDTO.toEntity();
        event.setCategory(categoryService.findById(eventDTO.getIdCategory()));

        eventDTO.getSections()
                .stream()
                .map(sectionDTO -> {
                    Section section = sectionDTO.toEntity();
                    sectionDTO.getTickets()
                            .stream()
                            .map(TicketDTO::toEntity)
                            .forEach(section::addTicket);
                    return section;
                })
                .forEach(event::addSection);
        repository.persistAndFlush(event);
        return event;
    }

    @Override
    public PanacheRepository<Event> getRepository() {
        return repository;
    }
}
