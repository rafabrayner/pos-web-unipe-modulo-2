package br.com.acme.areacomum;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AreasComunsRepository extends JpaRepository<AreaComumEntity, Long> {
	
	public List<AreaComumEntity> findByDescricaoContaining(String nome);

}
