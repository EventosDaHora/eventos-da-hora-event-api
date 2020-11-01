package com.eventosdahora.event.ms.repository;

import com.eventosdahora.event.ms.dominio.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {

}
