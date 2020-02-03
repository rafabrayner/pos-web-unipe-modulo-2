package br.com.acme.multa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultaService {
	
	@Autowired
	private MultasRepository repository;
	
	
	@Transactional(readOnly=true)
	public List<MultaEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public MultaEntity save(MultaEntity multa) {
		return this.repository.save(multa);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<MultaEntity> getById(Long id) {
		return this.repository.findById(id);
	}

}
