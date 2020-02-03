package br.com.acme.multa;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.condominio.CondominioModel;
import br.com.acme.unidade.UnidadeModel;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonRootName(value = "multa")
@Relation(collectionRelation = "multas")
@JsonInclude(Include.NON_NULL)
public class MultaModel extends RepresentationModel<MultaModel>{
	
	private Long id;
	private String descricaoMulta;
	private LocalDate dataMulta;
	private UnidadeModel unidadeMultada;
	private CondominioModel condominoMultado;
	private BigDecimal valorMulta;

}