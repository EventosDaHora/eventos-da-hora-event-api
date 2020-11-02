package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.ImageEvent;
import com.eventosdahora.event.ms.repository.ImageEventRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class ImageService extends GenericService<ImageEvent> {

    @Inject
    ImageEventRepository repository;

    @Override
    public PanacheRepository<ImageEvent> getRepository() {
        return repository;
    }
}
