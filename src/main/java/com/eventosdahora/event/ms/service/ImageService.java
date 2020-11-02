package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.ImageEvent;
import com.eventosdahora.event.ms.repository.ImageEventRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class ImageService {

    @Inject
    ImageEventRepository repository;

    public ImageEvent findById(Long imageId) {
        return repository.findById(imageId);
    }

}
