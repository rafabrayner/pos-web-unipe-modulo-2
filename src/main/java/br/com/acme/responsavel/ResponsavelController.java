package br.com.acme.responsavel;

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

import br.com.acme.responsavel.ResponsavelEntity;
import br.com.acme.responsavel.ResponsavelModel;
import br.com.acme.responsavel.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {
	
	@Autowired
	private ResponsavelService service;
	@Autowired
	private ResponsavelModelAssembler responsavelModelAssembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<ResponsavelModel>> getAll() {
		
		List<ResponsavelEntity> responsavelsEntities = this.service.list();

		return new ResponseEntity<>(responsavelModelAssembler.toCollectionModel(responsavelsEntities), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create")
	public ResponseEntity<ResponsavelModel> create(@RequestBody ResponsavelEntity responsavelEntity) {
		this.service.save(responsavelEntity);
		return new ResponseEntity<ResponsavelModel>(this.responsavelModelAssembler.toModel(responsavelEntity), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponsavelModel> delete(@PathVariable Long id) {
		Optional<ResponsavelEntity> responsavelOptional = this.service.getById(id);
		
		if (responsavelOptional.isPresent()) {
			ResponsavelEntity responsavelEntity = responsavelOptional.get();
			this.service.remove(responsavelEntity.getId());
			return ResponseEntity.ok().body(this.responsavelModelAssembler.toModel(responsavelEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponsavelModel> update(@PathVariable("id") Long id) {
		Optional<ResponsavelEntity> responsavelOptional = this.service.getById(id); 
		
		if(responsavelOptional.isPresent()) {
			ResponsavelEntity responsavelEntity = responsavelOptional.get();
			this.service.save(responsavelEntity);		
			return ResponseEntity.ok(this.responsavelModelAssembler.toModel(responsavelEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/responsavel/{id}")
	public ResponseEntity<ResponsavelModel> getById(@PathVariable Long id) {		
		Optional<ResponsavelEntity> responsavelOptional = this.service.getById(id);

		if(responsavelOptional.isPresent()) {
			ResponsavelEntity responsavelEntity = responsavelOptional.get();
			return ResponseEntity.ok(this.responsavelModelAssembler.toModel(responsavelEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
