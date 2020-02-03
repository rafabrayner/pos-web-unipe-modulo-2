package br.com.acme.unidade;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.condominio.CondominioModelAssembler;
import br.com.acme.multa.MultaModelAssembler;
import br.com.acme.responsavel.ResponsavelModelAssembler;
import br.com.acme.unidade.UnidadeController;
import br.com.acme.unidade.UnidadeEntity;
import br.com.acme.unidade.UnidadeModel;



@Component
public class UnidadeModelAssembler extends RepresentationModelAssemblerSupport<UnidadeEntity, UnidadeModel>{
	
	@Autowired
	CondominioModelAssembler condominioModelAssembler;
	@Autowired
	MultaModelAssembler multaModelAssembler;
	@Autowired
	ResponsavelModelAssembler responsavelModelAssembler;

	public UnidadeModelAssembler() {
		super(UnidadeController.class, UnidadeModel.class);
	}

	@Override
	public UnidadeModel toModel(UnidadeEntity unidadeEntity) {
		
		if(unidadeEntity == null) {
			return null;
		}
		
		UnidadeModel unidadeModel = UnidadeModel.builder().id(unidadeEntity.getId())
			.blocoUnidade(unidadeEntity.getBlocoUnidade())
			.condominioUnidade(this.condominioModelAssembler.toModel(unidadeEntity.getCondominioUnidade()))
			.multasUnidade(this.multaModelAssembler.toModel(unidadeEntity.getMultasUnidade()))
			.numeroUnidade(unidadeEntity.getNumeroUnidade())
			.responsavelUnidade(this.responsavelModelAssembler.toModel(unidadeEntity.getResponsavelUnidade()))
			.build();
		unidadeModel.add(linkTo(methodOn(UnidadeController.class).getById(unidadeEntity.getId())).withRel("GET Unidade " + unidadeEntity.getNumeroUnidade()).withType("GET"));
		unidadeModel.add(linkTo(methodOn(UnidadeController.class).update(unidadeEntity.getId())).withRel("UPDATE Unidade " + unidadeEntity.getNumeroUnidade()).withType("PUT"));
		unidadeModel.add(linkTo(methodOn(UnidadeController.class).delete(unidadeEntity.getId())).withRel("DELETE Unidade " + unidadeEntity.getNumeroUnidade()).withType("DELETE"));
		return unidadeModel;
	}
	
	public Set<UnidadeModel> toModel(Set<UnidadeEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<UnidadeModel> toModel(List<UnidadeEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
