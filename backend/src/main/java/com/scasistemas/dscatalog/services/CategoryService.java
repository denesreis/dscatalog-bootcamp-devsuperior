package com.scasistemas.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scasistemas.dscatalog.dto.CategoryDTO;
import com.scasistemas.dscatalog.entities.Category;
import com.scasistemas.dscatalog.repositories.CategoryRepository;
import com.scasistemas.dscatalog.services.exceptions.DatabaseException;
import com.scasistemas.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository; /*Para importar a dependencia do categoryrepository*/
	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest){
		Page<Category> list = repository.findAll(pageRequest);
		
		/**/
		return list.map(x -> new CategoryDTO( x ) );
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		
		/*O retorna da busca nunca vai ser um objeot nulo. Vai ser do tipo Optional e dentro o optional pode ter ou não a categoria*/
		Optional<Category> obj = repository.findById(id);
		//Category entity = obj.get(); // método get obtém o objeto que estava dentro do Optional
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não localizada"));
		return new CategoryDTO(entity); //Como o método retorno CategoryDTO e não a entidade precisa dar  new

	}
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		
		Category entity = new Category(); //Convertendo o DTO para uma entidade
		entity.setName(dto.getName());
		entity = repository.save(entity); //entity recebe a referencia dele mesmo (Gravando os dados)
		return new CategoryDTO(entity); //retornando a entidade convertida para um CategoryDTO

	}
	@Transactional
	public CategoryDTO update(Long id,CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id da categoria não localizadada "+id);
		
		}

	}
	//Não vai usar @Transactional para capturar uma execão que vai vir do banco de dados se colocar não dá pra tratar
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id de Categoria não localizada: "+id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integridade Violada ");
		}
		
		
		
		
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
