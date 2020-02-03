package br.com.acme.condominio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CondominioService {
	
	@Autowired
	private CondominiosRepository repository;
	
	@Transactional(readOnly=true)
	public List<CondominioEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public CondominioEntity save(CondominioEntity contato) {
		return this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<CondominioEntity> getById(Long id) {
		return this.repository.findById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<List<CondominioEntity>> getCondominiosComMultas() {
		return this.repository.findByMultasAplicadasIsNotNull();
	}
	
	@Transactional(readOnly=true)
	public Optional<List<CondominioEntity>> getCondominiosSemMultas() {
		return this.repository.findByMultasAplicadasIsNull();
	}
	

}
