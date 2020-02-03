package br.com.acme.reclamacao;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonRootName(value = "reclamacao")
@Relation(collectionRelation = "reclamacoes")
@JsonInclude(Include.NON_NULL)
public class ReclamacaoModel extends RepresentationModel<ReclamacaoModel> {
	
	private Long id;
	private String descricaoReclamacao;
	private LocalDate dataReclamacao;
	private ResponsavelModel responsavelReclamacao;

}
