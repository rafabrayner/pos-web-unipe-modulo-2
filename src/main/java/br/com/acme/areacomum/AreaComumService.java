package br.com.acme.areacomum;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AreaComumService {
	
	@Autowired
	private AreasComunsRepository repository;
	
	
	@Transactional(readOnly=true)
	public List<AreaComumEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public AreaComumEntity save(AreaComumEntity multa) {
		return this.repository.save(multa);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<AreaComumEntity> getById(Long id) {
		return this.repository.findById(id);
	}

}
