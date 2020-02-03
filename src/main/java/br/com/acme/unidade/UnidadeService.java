package br.com.acme.unidade;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UnidadeService {
	
	@Autowired
	private UnidadesRepository repository;
	
	@Transactional(readOnly=true)
	public List<UnidadeEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public UnidadeEntity save(UnidadeEntity contato) {
		return this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<UnidadeEntity> getById(Long id) {
		return this.repository.findById(id);
	}
	
	public Optional<List<UnidadeEntity>> getUnidadesComMulta() {
		return this.repository.findByMultasUnidadeIsNotNull();
	}
	
	public Optional<List<UnidadeEntity>> getUnidadesSemMulta() {
		return this.repository.findByMultasUnidadeIsNull();
	}

}
