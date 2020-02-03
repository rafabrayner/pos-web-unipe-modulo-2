package br.com.acme.condominio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominiosRepository extends JpaRepository<CondominioEntity, Long> {

	public List<CondominioEntity> findByNomeContaining(String nome);
	
	public Optional<List<CondominioEntity>> findByMultasAplicadasIsNotNull();
	
	public Optional<List<CondominioEntity>> findByMultasAplicadasIsNull();
}
