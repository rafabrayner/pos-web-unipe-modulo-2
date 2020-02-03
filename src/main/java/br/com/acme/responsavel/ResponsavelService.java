package br.com.acme.responsavel;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ResponsavelService {
	
	@Autowired
	private ResponsaveisRepository repository;
	
	@Transactional(readOnly=true)
	public List<ResponsavelEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(ResponsavelEntity contato) {
		this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<ResponsavelEntity> getById(Long id) {
		return this.repository.findById(id);
	}


}
