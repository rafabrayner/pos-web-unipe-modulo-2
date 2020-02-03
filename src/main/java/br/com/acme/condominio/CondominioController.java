package br.com.acme.condominio;

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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/condominios")
public class CondominioController {

	@Autowired
	private CondominioService service;

	@Autowired
	private CondominioModelAssembler condominioModelAssembler;
	
	@GetMapping("")
	public ResponseEntity<CollectionModel<CondominioModel>> getAll() {
		
		List<CondominioEntity> condominiosEntities = this.service.list();

		return new ResponseEntity<>(condominioModelAssembler.toCollectionModel(condominiosEntities), HttpStatus.OK);
	}

	@GetMapping("/condominio/{id}")
	public ResponseEntity<CondominioModel> getById(@PathVariable Long id) {
		return service.getById(id).map(condominioModelAssembler::toModel).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	//@ResponseStatus(code = HttpStatus.OK)
	@PostMapping("/create")
	public ResponseEntity<CondominioModel> save(@RequestBody CondominioEntity condominio) {
		CondominioEntity condominioEntity = this.service.save(condominio);
		CondominioModel condominioModel = this.condominioModelAssembler.toModel(condominioEntity);

		return new ResponseEntity<>(condominioModel, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CondominioModel> delete(@PathVariable Long id) {
		this.service.remove(id);
		Optional<CondominioEntity> condominioOptional = this.service.getById(id);
		if (condominioOptional.isPresent()) {
			CondominioEntity condominioEntity = condominioOptional.get();
			this.service.remove(condominioEntity.getId());
			return ResponseEntity.ok().body(this.condominioModelAssembler.toModel(condominioEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CondominioModel> update(@PathVariable("id") Long id) {
		Optional<CondominioEntity> condominioOptional = this.service.getById(id);
		if (condominioOptional.isPresent()) {
			CondominioEntity condominioEntity = condominioOptional.get();
			this.service.save(condominioEntity);
			return ResponseEntity.ok().body(this.condominioModelAssembler.toModel(condominioEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/com-multas")
	public ResponseEntity<CollectionModel<CondominioModel>> getCondominiosMultados() {
		Optional<List<CondominioEntity>> condominiosOptional = this.service.getCondominiosComMultas();
		if (condominiosOptional.isPresent()) {
			List<CondominioEntity> condominiosEntities = condominiosOptional.get();
			return ResponseEntity.ok().body(this.condominioModelAssembler.toCollectionModel(condominiosEntities));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/sem-multas")
	public ResponseEntity<CollectionModel<CondominioModel>> getCondominiosSemMultas() {
		Optional<List<CondominioEntity>> condominiosOptional = this.service.getCondominiosSemMultas();
		if (condominiosOptional.isPresent()) {
			List<CondominioEntity> condominiosEntities = condominiosOptional.get();
			return ResponseEntity.ok().body(this.condominioModelAssembler.toCollectionModel(condominiosEntities));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	

}
