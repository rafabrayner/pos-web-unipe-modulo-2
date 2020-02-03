package br.com.acme.aviso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@RequestMapping("/avisos")
public class AvisoController {
	
	@Autowired
	private AvisoService service;
	@Autowired
	private AvisoModelAssembler avisoModelAssembler;
	
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<AvisoModel>> getAll() {
		
		List<AvisoEntity> avisosEntities = this.service.list();

		return new ResponseEntity<>(this.avisoModelAssembler.toCollectionModel(avisosEntities), HttpStatus.OK);
	}
	
	/**
	 * Condomino recebe aviso
	 * 
	 * @param avisoEntity
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<AvisoModel> save(@RequestBody AvisoEntity avisoEntity) {
		this.service.save(avisoEntity);
		return new ResponseEntity<AvisoModel>(this.avisoModelAssembler.toModel(avisoEntity), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AvisoModel> delete(@PathVariable Long id) {
		Optional<AvisoEntity> avisoOptional = this.service.getById(id);
		
		if (avisoOptional.isPresent()) {
			AvisoEntity avisoEntity = avisoOptional.get();
			this.service.remove(avisoEntity.getId());
			return ResponseEntity.ok().body(this.avisoModelAssembler.toModel(avisoEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<AvisoModel> update(@PathVariable("id") Long id) {
		Optional<AvisoEntity> avisoOptional = this.service.getById(id);
		
		if (avisoOptional.isPresent()) {
			AvisoEntity avisoEntity = avisoOptional.get();
			this.service.save(avisoEntity);
			return ResponseEntity.ok().body(this.avisoModelAssembler.toModel(avisoEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping("/aviso/{id}")
	public ResponseEntity<AvisoModel> getById(@PathVariable("id") Long id) {
		Optional<AvisoEntity> avisoOptional = this.service.getById(id);
		
		if (avisoOptional.isPresent()) {
			AvisoEntity avisoEntity = avisoOptional.get();
			return ResponseEntity.ok().body(this.avisoModelAssembler.toModel(avisoEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	

	
	
}
