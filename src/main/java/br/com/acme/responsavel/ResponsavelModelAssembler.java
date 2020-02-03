package br.com.acme.responsavel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.reclamacao.ReclamacaoModelAssembler;

@Component
public class ResponsavelModelAssembler extends RepresentationModelAssemblerSupport<ResponsavelEntity, ResponsavelModel> {
	
	@Autowired
	ReclamacaoModelAssembler reclamacaoModelAssembler;
	
	public ResponsavelModelAssembler() {
		super(ResponsavelController.class, ResponsavelModel.class);
	}

	@Override
	public ResponsavelModel toModel(ResponsavelEntity responsavelEntity) {
		
		if(responsavelEntity == null) {
			return null;
		}
		ResponsavelModel responsavelModel = instantiateModel(responsavelEntity);

		responsavelModel.setId(responsavelEntity.getId());
		responsavelModel.setEmail(responsavelEntity.getEmail());
		responsavelModel.setNome(responsavelEntity.getNome());
		responsavelModel.setReclamacoes(this.reclamacaoModelAssembler.toModel(responsavelEntity.getReclamacoes()));
		
		responsavelModel.add(linkTo(methodOn(ResponsavelController.class).getById(responsavelEntity.getId())).withRel("GET Responsavel " + responsavelEntity.getNome()).withType("GET"));
		responsavelModel.add(linkTo(methodOn(ResponsavelController.class).update(responsavelEntity.getId())).withRel("UPDATE Responsavel " + responsavelEntity.getNome()).withType("PUT"));
		responsavelModel.add(linkTo(methodOn(ResponsavelController.class).delete(responsavelEntity.getId())).withRel("DELETE Responsavel " + responsavelEntity.getNome()).withType("DELETE"));

		return responsavelModel;
	}
	
	public Set<ResponsavelModel> toModel(Set<ResponsavelEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<ResponsavelModel> toModel(List<ResponsavelEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
