package br.com.acme.multa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MultasRepository extends JpaRepository<MultaEntity, Long> {
	
	public List<MultaEntity> findByDescricaoMultaContaining(String nome);

}
