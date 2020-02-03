package br.com.acme.areacomum;

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
@RequestMapping("/areas-comuns")
public class AreaComumController {
	
	@Autowired
	private AreaComumService service;
	@Autowired
	private AreaComumModelAssembler areaComumModelAssembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<AreaComumModel>> getAll() {
		
		List<AreaComumEntity> areasComunsEntities = this.service.list();

		return new ResponseEntity<>(this.areaComumModelAssembler.toCollectionModel(areasComunsEntities), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create")
	public ResponseEntity<AreaComumModel> create(@RequestBody AreaComumEntity areaComumEntity) {
		this.service.save(areaComumEntity);
		return new ResponseEntity<AreaComumModel>(this.areaComumModelAssembler.toModel(areaComumEntity), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<AreaComumModel> delete(@PathVariable Long id) {
		Optional<AreaComumEntity> areaComumOptional = this.service.getById(id);
		
		if (areaComumOptional.isPresent()) {
			AreaComumEntity areaComumEntity = areaComumOptional.get();
			this.service.remove(areaComumEntity.getId());
			return ResponseEntity.ok().body(this.areaComumModelAssembler.toModel(areaComumEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<AreaComumModel> update(@PathVariable("id") Long id) {
		Optional<AreaComumEntity> areaComumOptional = this.service.getById(id); 
		
		if(areaComumOptional.isPresent()) {
			AreaComumEntity areaComumEntity = areaComumOptional.get();
			this.service.save(areaComumEntity);		
			return ResponseEntity.ok(this.areaComumModelAssembler.toModel(areaComumEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/area-comum/{id}")
	public ResponseEntity<AreaComumModel> getById(@PathVariable Long id) {
		Optional<AreaComumEntity> areaComumOptional = this.service.getById(id);
		
		if(areaComumOptional.isPresent()) {
			AreaComumEntity areaComumEntity = areaComumOptional.get();
			this.service.save(areaComumEntity);		
			return ResponseEntity.ok(this.areaComumModelAssembler.toModel(areaComumEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
