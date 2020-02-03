package br.com.acme.reclamacao;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.reclamacao.ReclamacaoController;
import br.com.acme.reclamacao.ReclamacaoEntity;
import br.com.acme.reclamacao.ReclamacaoModel;
import br.com.acme.responsavel.ResponsavelModelAssembler;

@Component
public class ReclamacaoModelAssembler extends RepresentationModelAssemblerSupport<ReclamacaoEntity, ReclamacaoModel> {
	
	@Autowired
	ResponsavelModelAssembler responsavelModelAssembler;
	
	public ReclamacaoModelAssembler() {
		super(ReclamacaoController.class, ReclamacaoModel.class);
	}

	@Override
	public ReclamacaoModel toModel(ReclamacaoEntity reclamacaoEntity) {
		
		if(reclamacaoEntity == null) {
			return null;
		}
		ReclamacaoModel reclamacaoModel = instantiateModel(reclamacaoEntity);

		reclamacaoModel.setId(reclamacaoEntity.getId());
		reclamacaoModel.setDataReclamacao(reclamacaoEntity.getDataReclamacao());
		reclamacaoModel.setDescricaoReclamacao(reclamacaoEntity.getDescricaoReclamacao());
		reclamacaoModel.setResponsavelReclamacao(this.responsavelModelAssembler.toModel(reclamacaoEntity.getResponsavelReclamacao()));
		
		reclamacaoModel.add(linkTo(methodOn(ReclamacaoController.class).getById(reclamacaoEntity.getId())).withRel("GET Reclamacao " + reclamacaoEntity.getId()).withType("GET"));
		reclamacaoModel.add(linkTo(methodOn(ReclamacaoController.class).update(reclamacaoEntity.getId())).withRel("UPDATE Reclamacao " + reclamacaoEntity.getId()).withType("PUT"));
		reclamacaoModel.add(linkTo(methodOn(ReclamacaoController.class).delete(reclamacaoEntity.getId())).withRel("DELETE Reclamacao " + reclamacaoEntity.getId()).withType("DELETE"));

		return reclamacaoModel;
	}
	
	public Set<ReclamacaoModel> toModel(Set<ReclamacaoEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<ReclamacaoModel> toModel(List<ReclamacaoEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
