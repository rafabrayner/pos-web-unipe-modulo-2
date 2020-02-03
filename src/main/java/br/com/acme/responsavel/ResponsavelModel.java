package br.com.acme.responsavel;

import java.util.Set;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.reclamacao.ReclamacaoModel;
import br.com.acme.reserva.ReservaModel;
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
@JsonRootName(value = "responsavel")
@Relation(collectionRelation = "responsaveis")
@JsonInclude(Include.NON_NULL)
public class ResponsavelModel extends RepresentationModel<ResponsavelModel>{
	
private Long id;
	
	private String nome;
	private String email;
	private String telefone;
	private Set<ReservaModel> reservas;
	private Set<ReclamacaoModel> reclamacoes;

}
