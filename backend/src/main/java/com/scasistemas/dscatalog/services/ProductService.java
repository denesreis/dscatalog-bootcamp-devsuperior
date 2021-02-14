package com.scasistemas.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scasistemas.dscatalog.dto.ProductDTO;
import com.scasistemas.dscatalog.entities.Product;
import com.scasistemas.dscatalog.repositories.ProductRepository;
import com.scasistemas.dscatalog.services.exceptions.DatabaseException;
import com.scasistemas.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository; /*Para importar a dependencia do categoryrepository*/
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		Page<Product> list = repository.findAll(pageRequest);
		
		/**/
		return list.map(x -> new ProductDTO( x ) );
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		/*O retorna da busca nunca vai ser um objeot nulo. Vai ser do tipo Optional e dentro o optional pode ter ou não a categoria*/
		Optional<Product> obj = repository.findById(id);
		//Product entity = obj.get(); // método get obtém o objeto que estava dentro do Optional
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não localizada"));
		return new ProductDTO(entity, entity.getCategories()); //Como o método retorno ProductDTO e não a entidade precisa dar  new

	}
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product(); //Convertendo o DTO para uma entidade
		//entity.setName(dto.getName());
		entity = repository.save(entity); //entity recebe a referencia dele mesmo (Gravando os dados)
		return new ProductDTO(entity); //retornando a entidade convertida para um ProductDTO

	}
	@Transactional
	public ProductDTO update(Long id,ProductDTO dto) {
		try {
			Product entity = repository.getOne(id);
			//entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
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
	

	
}
