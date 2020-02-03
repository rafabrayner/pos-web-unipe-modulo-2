package br.com.acme.reclamacao;

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

import br.com.acme.reclamacao.ReclamacaoEntity;
import br.com.acme.reclamacao.ReclamacaoModel;
import br.com.acme.reclamacao.ReclamacaoModelAssembler;
import br.com.acme.reclamacao.ReclamacaoService;

@RestController
@RequestMapping("/reclamacoes")
public class ReclamacaoController {
	
	@Autowired
	private ReclamacaoService service;
	@Autowired
	private ReclamacaoModelAssembler reclamacaoModelAssembler;
	
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("")
	public ResponseEntity<CollectionModel<ReclamacaoModel>> getAll() {
		
		List<ReclamacaoEntity> reclamacoesEntities = this.service.list();

		return new ResponseEntity<>(reclamacaoModelAssembler.toCollectionModel(reclamacoesEntities), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/create")
	public ResponseEntity<ReclamacaoModel> create(@RequestBody ReclamacaoEntity reclamacaoEntity) {
		this.service.save(reclamacaoEntity);
		return new ResponseEntity<ReclamacaoModel>(this.reclamacaoModelAssembler.toModel(reclamacaoEntity), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ReclamacaoModel> delete(@PathVariable Long id) {
		Optional<ReclamacaoEntity> reclamacaoOptional = this.service.getById(id);
		
		if (reclamacaoOptional.isPresent()) {
			ReclamacaoEntity reclamacaoEntity = reclamacaoOptional.get();
			this.service.remove(reclamacaoEntity.getId());
			return ResponseEntity.ok().body(this.reclamacaoModelAssembler.toModel(reclamacaoEntity));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@PutMapping("/update/{id}")
	public ResponseEntity<ReclamacaoModel> update(@PathVariable("id") Long id) {
		Optional<ReclamacaoEntity> reclamacaoOptional = this.service.getById(id); 
		
		if(reclamacaoOptional.isPresent()) {
			ReclamacaoEntity reclamacaoEntity = reclamacaoOptional.get();
			this.service.save(reclamacaoEntity);		
			return ResponseEntity.ok(this.reclamacaoModelAssembler.toModel(reclamacaoEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ResponseStatus(code = HttpStatus.FOUND)
	@GetMapping("/reclamacao/{id}")
	public ResponseEntity<ReclamacaoModel> getById(@PathVariable Long id) {		
		Optional<ReclamacaoEntity> reclamacaoOptional = this.service.getById(id);

		if(reclamacaoOptional.isPresent()) {
			ReclamacaoEntity reclamacaoEntity = reclamacaoOptional.get();
			return ResponseEntity.ok(this.reclamacaoModelAssembler.toModel(reclamacaoEntity));
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
