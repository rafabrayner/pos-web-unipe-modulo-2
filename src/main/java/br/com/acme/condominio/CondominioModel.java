package br.com.acme.condominio;

import java.util.Set;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.aviso.AvisoModel;
import br.com.acme.multa.MultaModel;
import br.com.acme.unidade.UnidadeModel;

import com.fasterxml.jackson.annotation.JsonRootName;

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
@JsonRootName(value = "condominio")
@Relation(collectionRelation = "condominios")
@JsonInclude(Include.NON_NULL)
public class CondominioModel extends RepresentationModel<CondominioModel>{
	
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
	private Set<MultaModel> multasAplicadas;
	private Set<UnidadeModel> unidades;
	private Set<AvisoModel> avisos;

}
