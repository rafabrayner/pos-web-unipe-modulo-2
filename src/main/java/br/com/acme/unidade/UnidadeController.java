package br.com.acme.unidade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.unidade.UnidadeEntity;


@RestController
@RequestMapping("/unidades")
public class UnidadeController {
	
	@Autowired
	private UnidadeService service;
	@Autowired
	private UnidadeModelAssembler unidadeModelAssembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<UnidadeModel>> getAll() {
		
		List<UnidadeEntity> unidadesEntities = this.service.list();

		return new ResponseEntity<>(unidadeModelAssembler.toCollectionModel(unidadesEntities), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create")
	public ResponseEntity<UnidadeModel> create(@RequestBody UnidadeEntity unidadeEntity) {
		this.service.save(unidadeEntity);
		return new ResponseEntity<UnidadeModel>(this.unidadeModelAssembler.toModel(unidadeEntity), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UnidadeModel> delete(@PathVariable Long id) {
		Optional<UnidadeEntity> unidadeOptional = this.service.getById(id);
		
		if (unidadeOptional.isPresent()) {
			UnidadeEntity unidadeEntity = unidadeOptional.get();
			this.service.remove(unidadeEntity.getId());
			return ResponseEntity.ok().body(this.unidadeModelAssembler.toModel(unidadeEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<UnidadeModel> update(@PathVariable("id") Long id) {
		Optional<UnidadeEntity> unidadeOptional = this.service.getById(id); 
		
		if(unidadeOptional.isPresent()) {
			UnidadeEntity unidadeEntity = unidadeOptional.get();
			this.service.save(unidadeEntity);		
			return ResponseEntity.ok(this.unidadeModelAssembler.toModel(unidadeEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/unidade/{id}")
	public ResponseEntity<UnidadeModel> getById(@PathVariable Long id) {		
		Optional<UnidadeEntity> unidadeOptional = this.service.getById(id); 
		
		if(unidadeOptional.isPresent()) {
			UnidadeEntity unidadeEntity = unidadeOptional.get();
			return ResponseEntity.ok(this.unidadeModelAssembler.toModel(unidadeEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * Filtrar unidades com multas
	 * 
	 * @return
	 */
	@GetMapping("/com-multas")
	public ResponseEntity<CollectionModel<UnidadeModel>> getUnidadesComMultas() {
		Optional<List<UnidadeEntity>> unidadesOptional = this.service.getUnidadesComMulta();
		
		if (unidadesOptional.isPresent()) {
			List<UnidadeEntity> unidadesEntities = unidadesOptional.get();
			return ResponseEntity.ok().body(this.unidadeModelAssembler.toCollectionModel(unidadesEntities));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	/**
	 * Filtra unidades sem multas
	 * 
	 * @return
	 */
	@GetMapping("/sem-multas")
	public ResponseEntity<CollectionModel<UnidadeModel>> getUnidadesSemMulta() {
		Optional<List<UnidadeEntity>> unidadesOptional = this.service.getUnidadesSemMulta();
		
		if (unidadesOptional.isPresent()) {
			List<UnidadeEntity> unidadesEntities = unidadesOptional.get();
			return ResponseEntity.ok().body(this.unidadeModelAssembler.toCollectionModel(unidadesEntities));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	 
	

}
