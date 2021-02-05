package com.scasistemas.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.scasistemas.dscatalog.dto.CategoryDTO;
import com.scasistemas.dscatalog.services.CategoryService;

@RestController /*Anotation para configurar a classe como controlador rest*/
@RequestMapping(value = "/categories") /*Informa a rota rest do recurso*/
public class CategoryResource {
	
	@Autowired  /*Para injetar automaticamente a dependencia*/
	private CategoryService service; /*Para declarar a dependencia do categoryservice*/
	
	/* ResponseEntity é Objeto do spring que vai encapsular uma resposta http*/
	@GetMapping /*Indica que este método vai ser um webservice --- */
	public ResponseEntity<List<CategoryDTO>> findAll(){
		
		/*List<Category> list = new ArrayList<>();No java List é uma interface e não pode ser instaciada diretamente por isto foi usado o ArrayList
		list.add(new Category(1L,"Books")); O "L" no código é para indicar que o numero é do tipo Long
		list.add(new Category(2L,"Eletronics"));
		list.add(new Category(3L,"Toys")); */
		
		List<CategoryDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list); /*O Ponto .ok deixa retornar uma resposta 200  que signfica que a requeisição foi realizada com sucesso*/
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){

		CategoryDTO dto = service.findById(id);
		
		return ResponseEntity.ok().body(dto); 
	}	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
		dto = service.insert(dto); //Método criado na camada de serviço
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") //URI -> Java.net.uri
				.buildAndExpand(dto.getId()).toUri();  //O comando todo para retornar a URI

		return ResponseEntity.created(uri).body(dto); 
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id,@RequestBody CategoryDTO dto){
		dto = service.update(id,dto); //Método criado na camada de serviço
		return ResponseEntity.ok().body(dto); 
	}
	
}
