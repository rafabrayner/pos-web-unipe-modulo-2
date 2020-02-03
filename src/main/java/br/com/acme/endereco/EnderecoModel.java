package br.com.acme.endereco;

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
@JsonRootName(value = "endereco")
@Relation(collectionRelation = "enderecos")
@JsonInclude(Include.NON_NULL)
public class EnderecoModel extends RepresentationModel<EnderecoModel> {
	
	private Long id;
	private String cidade;
	private String rua;
	private String cep;
	private CondominioModel condominioEndereco;

}
