package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.City;
import com.eventosdahora.event.ms.dominio.Country;
import com.eventosdahora.event.ms.dominio.Event;
import com.eventosdahora.event.ms.dominio.Section;
import com.eventosdahora.event.ms.dto.EventDTO;
import com.eventosdahora.event.ms.dto.ImageEventDTO;
import com.eventosdahora.event.ms.dto.SectionDTO;
import com.eventosdahora.event.ms.dto.TicketDTO;
import com.eventosdahora.event.ms.repository.EventRepository;
import com.eventosdahora.event.ms.repository.StatusEventRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.LongStream;

@Log
@ApplicationScoped
public class EventService extends GenericService<Event> {

    @Inject
    EventRepository repository;

    @Inject
    CategoryService categoryService;

    @Inject
    StatusEventRepository statusEventRepository;

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

        event.setStatus(statusEventRepository.findById(3L));

        event.getLocalization().setCity(new City(null, eventDTO.getLocalization().getCity()));

        event.getLocalization().setCountry(new Country(null, eventDTO.getLocalization().getCountry()));

        addImages(event, eventDTO.getImages());

        addSections(event, eventDTO.getSections());

        repository.persistAndFlush(event);

        return event;
    }

    private void addSections(Event event, List<SectionDTO> sections) {
        sections.stream()
                .map(sectionDTO -> {
                    Section section = sectionDTO.toEntity();
                    LongStream.rangeClosed(1, sections.size())
                            .mapToObj(qtd -> new TicketDTO(sectionDTO.getQtdTickets()))
                            .map(TicketDTO::toEntity)
                            .forEach(section::addTicket);
                    return section;
                }).forEach(event::addSection);
    }

    private void addImages(Event event, List<ImageEventDTO> images) {
        images.stream()
                .map(ImageEventDTO::toEntity)
                .forEach(event::addImage);
    }
    
    public List<Event> getAll(String param){
       if(param != null){
           return this.repository.findByParameters(param);
       }else{
           return getRepository().listAll();
       }
    }

    @Override
    public PanacheRepository<Event> getRepository() {
        return repository;
    }
}
