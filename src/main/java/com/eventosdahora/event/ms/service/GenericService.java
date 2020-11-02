package com.eventosdahora.event.ms.service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<E> {

    public E findById(Long id) {
        return getRepository().findById(id);
    }

    public Optional<E> findByIdOptional(Long id) {
        return getRepository().findByIdOptional(id);
    }

    public List<E> findAll() {
        return getRepository().findAll().list();
    }

    public abstract PanacheRepository<E> getRepository();

}
