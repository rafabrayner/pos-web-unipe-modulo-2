package br.com.acme.multa;

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



@RestController
@RequestMapping("/multas")
public class MultaController {
	
	@Autowired
	private MultaService service;
	@Autowired
	private MultaModelAssembler multaModelAssembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<MultaModel>> getAll() {
		
		List<MultaEntity> multasEntities = this.service.list();

		return new ResponseEntity<>(multaModelAssembler.toCollectionModel(multasEntities), HttpStatus.OK);
	}
	
	/**
	 * Unidade recebe multa
	 * 
	 * @param multaEntity
	 * @return
	 */
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create")
	public ResponseEntity<MultaModel> create(@RequestBody MultaEntity multaEntity) {
		this.service.save(multaEntity);
		return new ResponseEntity<MultaModel>(this.multaModelAssembler.toModel(multaEntity), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<MultaModel> delete(@PathVariable Long id) {
		
		Optional<MultaEntity> multaOptional = this.service.getById(id);
		if (multaOptional.isPresent()) {
			MultaEntity multaEntity = multaOptional.get();
			this.service.remove(multaEntity.getId());
			return ResponseEntity.ok().body(this.multaModelAssembler.toModel(multaEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<MultaModel> update(@PathVariable("id") Long id) {
		Optional<MultaEntity> multaOptional = this.service.getById(id); 
		
		if(multaOptional.isPresent()) {
			MultaEntity multaEntity = multaOptional.get();
			this.service.save(multaEntity);		
			return ResponseEntity.ok(this.multaModelAssembler.toModel(multaEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/multa/{id}")
	public ResponseEntity<MultaModel> getById(@PathVariable Long id) {
		Optional<MultaEntity> multaOptional = this.service.getById(id);
		
		if(multaOptional.isPresent()) {
			MultaEntity multaEntity = multaOptional.get();
			return ResponseEntity.ok(this.multaModelAssembler.toModel(multaEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	

}
