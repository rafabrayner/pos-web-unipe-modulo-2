package br.com.acme.aviso;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvisoService {

	@Autowired
	private AvisosRepository repository;
	
	@Transactional(readOnly=true)
	public List<AvisoEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public AvisoEntity save(AvisoEntity aviso) {
		return this.repository.save(aviso);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<AvisoEntity> getById(Long id) {
		return this.repository.findById(id);
	}
	
}
