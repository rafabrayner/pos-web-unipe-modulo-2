package br.com.acme.reclamacao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReclamacaoService {
	
	@Autowired
	private ReclamacoesRepository repository;
	
	@Transactional(readOnly=true)
	public List<ReclamacaoEntity> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public ReclamacaoEntity save(ReclamacaoEntity contato) {
		return this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<ReclamacaoEntity> getById(Long id) {
		return this.repository.findById(id);
	}
	

}
