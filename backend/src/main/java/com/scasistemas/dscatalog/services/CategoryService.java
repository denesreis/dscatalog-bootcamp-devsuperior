package com.scasistemas.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scasistemas.dscatalog.dto.CategoryDTO;
import com.scasistemas.dscatalog.entities.Category;
import com.scasistemas.dscatalog.repositories.CategoryRepository;
import com.scasistemas.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository; /*Para importar a dependencia do categoryrepository*/
	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		
		/**/
		return list.stream().map(x -> new CategoryDTO( x ) ).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		/*O retorna da busca nunca vai ser um objeot nulo. Vai ser do tipo Optional e dentro o optional pode ter ou não a categoria*/
		Optional<Category> obj = repository.findById(id);
		//Category entity = obj.get(); // método get obtém o objeto que estava dentro do Optional
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entidade não localizada"));
		return new CategoryDTO(entity); //Como o método retorno CategoryDTO e não a entidade precisa dar  new

	}
	
	
	
	 /* 
	public List<Category> findAll(){
	return repository.findAll(); Não vai mais retornar a entidade e sim um DTO   
	}*/
	
	/*public List<CategoryDTO> findAll(){ Uma maneira de fazer seria transformando em uma array de lista
		List<CategoryDTO> list = repository.findAll();
		
		List<CategoryDTO> listDto = new ArrayList<>();
		for (Category cat : list) {
			listDto.add(new CategoryDTO(cat));
		}
		return listDto;
	 
	}*/
	
	
}
