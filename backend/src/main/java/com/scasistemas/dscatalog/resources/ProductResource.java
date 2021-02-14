package com.scasistemas.dscatalog.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scasistemas.dscatalog.dto.ProductDTO;
import com.scasistemas.dscatalog.services.ProductService;

@RestController /*Anotation para configurar a classe como controlador rest*/
@RequestMapping(value = "/products") /*Informa a rota rest do recurso*/
public class ProductResource {
	
		@Autowired  /*Para injetar automaticamente a dependencia*/
		private ProductService service; /*Para declarar a dependencia do productservice*/
		
		/* ResponseEntity é Objeto do spring que vai encapsular uma resposta http*/
		@GetMapping /*Indica que este método vai ser um webservice --- */
		public ResponseEntity<Page<ProductDTO>> findAll(
				@RequestParam(value = "page", defaultValue = "0") Integer page,
				@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
				@RequestParam(value = "direction", defaultValue = "ASC") String direction,
				@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
				){
			
			
			PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy); //Direction.valueOf(direction) Na requisição hhtp mexe só com string neste caso vai converter a string para um tipo enumerado
			
			Page<ProductDTO> list = service.findAllPaged(pageRequest);
			
			return ResponseEntity.ok().body(list); /*O Ponto .ok deixa retornar uma resposta 200  que signfica que a requeisição foi realizada com sucesso*/
		}
		
		@GetMapping(value = "/{id}")
		public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
	
			ProductDTO dto = service.findById(id);
			
			return ResponseEntity.ok().body(dto); 
		}	
		@PostMapping
		public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto){
			dto = service.insert(dto); //Método criado na camada de serviço
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") //URI -> Java.net.uri
					.buildAndExpand(dto.getId()).toUri();  //O comando todo para retornar a URI
	
			return ResponseEntity.created(uri).body(dto); 
			
		}
		
		@PutMapping(value = "/{id}")
		public ResponseEntity<ProductDTO> update(@PathVariable Long id,@RequestBody ProductDTO dto){
			dto = service.update(id,dto); //Método criado na camada de serviço
			return ResponseEntity.ok().body(dto); 
		}
		
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<ProductDTO> delete(@PathVariable Long id){
			service.delete(id); //Método criado na camada de serviço
			return ResponseEntity.noContent().build(); 
		}
		
	}
	

