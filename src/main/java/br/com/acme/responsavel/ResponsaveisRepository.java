package br.com.acme.responsavel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsaveisRepository extends JpaRepository<ResponsavelEntity, Long> {
	
	public List<ResponsavelEntity> findByNomeContaining(String nome);

}
