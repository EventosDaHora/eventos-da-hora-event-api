package com.eventosdahora.event.ms.service;

import com.eventosdahora.event.ms.dominio.Category;
import com.eventosdahora.event.ms.repository.CategoryRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@Log
@ApplicationScoped
public class CategoryService {
	
	@Inject
	private CategoryRepository categoryRepository;
	
	public List<Category> getAll() {
		return categoryRepository.listAll();
	}
	
}
