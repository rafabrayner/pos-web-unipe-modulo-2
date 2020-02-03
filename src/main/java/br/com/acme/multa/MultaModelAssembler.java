package br.com.acme.multa;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.condominio.CondominioModelAssembler;
import br.com.acme.unidade.UnidadeModelAssembler;

@Component
public class MultaModelAssembler extends RepresentationModelAssemblerSupport<MultaEntity, MultaModel> {

	@Autowired
	CondominioModelAssembler condominioModelAssembler;
	@Autowired
	UnidadeModelAssembler unidadeModelAssembler;
	
	public MultaModelAssembler() {
		super(MultaController.class, MultaModel.class);
	}

	@Override
	public MultaModel toModel(MultaEntity multaEntity) {
		if(multaEntity == null) {
			return null;
		}
		
		MultaModel multaModel = MultaModel.builder()
				.id(multaEntity.getId())
				.condominoMultado(this.condominioModelAssembler.toModel(multaEntity.getCondominoMultado()))
				.dataMulta(multaEntity.getDataMulta())
				.descricaoMulta(multaEntity.getDescricaoMulta())
				.unidadeMultada(this.unidadeModelAssembler.toModel(multaEntity.getUnidadeMultada()))
				.valorMulta(multaEntity.getValorMulta())
				.build();
		multaModel.add(linkTo(methodOn(MultaController.class).getById(multaEntity.getId())).withRel("GET Multa " + multaEntity.getId()).withType("GET"));
		multaModel.add(linkTo(methodOn(MultaController.class).update(multaEntity.getId())).withRel("UPDATE Multa " + multaEntity.getId()).withType("PUT"));
		multaModel.add(linkTo(methodOn(MultaController.class).delete(multaEntity.getId())).withRel("DELETE Multa " + multaEntity.getId()).withType("DELETE"));
		return multaModel;
	}
	
	public Set<MultaModel> toModel(Set<MultaEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<MultaModel> toModel(List<MultaEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
