package br.com.acme.aviso;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.condominio.CondominioModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "aviso")
@Relation(collectionRelation = "avisos")
@JsonInclude(Include.NON_NULL)
public class AvisoModel extends RepresentationModel<AvisoModel>{
	
	private Long id;
	private String descricaoAviso;
	private CondominioModel condominioAviso;

}
