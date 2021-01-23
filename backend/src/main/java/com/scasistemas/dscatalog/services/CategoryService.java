package com.scasistemas.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scasistemas.dscatalog.entities.Category;
import com.scasistemas.dscatalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository; /*Para importar a dependencia do categoryrepository*/
	@Transactional(readOnly = true)
	public List<Category> findAll(){
		
		return repository.findAll();
		
	}
}