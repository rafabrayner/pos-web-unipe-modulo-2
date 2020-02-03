package br.com.acme.reclamacao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamacoesRepository extends JpaRepository<ReclamacaoEntity, Long> {
	
	public List<ReclamacaoEntity> findByResponsavelReclamacaoNomeContaining(String nome);

}
