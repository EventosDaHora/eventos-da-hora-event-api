package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Category;
import com.eventosdahora.event.ms.repository.CategoryRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class CategoryService extends GenericService<Category> {
	
	@Inject
	CategoryRepository categoryRepository;

	@Override
	public PanacheRepository<Category> getRepository() {
		return categoryRepository;
	}
}
