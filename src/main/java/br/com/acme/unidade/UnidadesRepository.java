package br.com.acme.unidade;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UnidadesRepository extends JpaRepository<UnidadeEntity, Long> {

	public List<UnidadeEntity> findByBlocoUnidadeContaining(String nome);
	
	public Optional<List<UnidadeEntity>> findByMultasUnidadeIsNotNull();
	
	public Optional<List<UnidadeEntity>> findByMultasUnidadeIsNull();
}
