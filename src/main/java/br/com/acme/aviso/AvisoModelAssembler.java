package br.com.acme.aviso;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.condominio.CondominioModelAssembler;


@Component
public class AvisoModelAssembler extends RepresentationModelAssemblerSupport<AvisoEntity, AvisoModel>{
	
	@Autowired
	CondominioModelAssembler condominioModelAssembler;
	
	public AvisoModelAssembler() {
		super(AvisoController.class, AvisoModel.class);
	}

	@Override
	public AvisoModel toModel(AvisoEntity avisoEntity) {
		
		if(avisoEntity == null) {
			return null;
		}
		
		AvisoModel avisoModel = instantiateModel(avisoEntity);

		avisoModel.setId(avisoEntity.getId());
		avisoModel.setCondominioAviso(this.condominioModelAssembler.toModel(avisoEntity.getCondominioAviso()));
		avisoModel.setDescricaoAviso(avisoEntity.getDescricaoAviso());
		
		avisoModel.add(linkTo(methodOn(AvisoController.class).getById(avisoEntity.getId())).withRel("GET Aviso " + avisoEntity.getId()).withType("GET"));
		avisoModel.add(linkTo(methodOn(AvisoController.class).update(avisoEntity.getId())).withRel("UPDATE Aviso " + avisoEntity.getId()).withType("PUT"));
		avisoModel.add(linkTo(methodOn(AvisoController.class).delete(avisoEntity.getId())).withRel("DELETE Aviso " + avisoEntity.getId()).withType("DELETE"));

		return avisoModel;
	}
	
	public Set<AvisoModel> toModel(Set<AvisoEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<AvisoModel> toModel(List<AvisoEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
