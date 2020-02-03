package br.com.acme.unidade;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.condominio.CondominioModel;
import br.com.acme.multa.MultaModel;
import br.com.acme.responsavel.ResponsavelModel;
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
@JsonRootName(value = "unidade")
@Relation(collectionRelation = "unidades")
@JsonInclude(Include.NON_NULL)
public class UnidadeModel extends RepresentationModel<UnidadeModel> {
	
	private Long id;
	private ResponsavelModel responsavelUnidade;
	private String numeroUnidade;
	private String blocoUnidade;
	private CondominioModel condominioUnidade;
	
	private Set<MultaModel> multasUnidade;
}
