package br.com.acme.aviso;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisosRepository extends JpaRepository<AvisoEntity, Long> {
	
	public List<AvisoEntity> findByDescricaoAvisoContaining(String nome);

}
