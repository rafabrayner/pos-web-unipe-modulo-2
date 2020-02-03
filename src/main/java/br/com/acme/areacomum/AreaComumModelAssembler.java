package br.com.acme.areacomum;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AreaComumModelAssembler extends RepresentationModelAssemblerSupport<AreaComumEntity, AreaComumModel>{
	
	public AreaComumModelAssembler() {
		super(AreaComumController.class, AreaComumModel.class);
	}

	@Override
	public AreaComumModel toModel(AreaComumEntity areaComumEntity) {
		
		if(areaComumEntity == null) {
			return null;
		}
		AreaComumModel areaComumModel = instantiateModel(areaComumEntity);

		areaComumModel.setId(areaComumEntity.getId());
		areaComumModel.setDescricao(areaComumEntity.getDescricao());
		areaComumModel.setCodigo(areaComumEntity.getCodigo());
		
		areaComumModel.add(linkTo(methodOn(AreaComumController.class).getById(areaComumEntity.getId())).withRel("GET AreaComum " + areaComumEntity.getCodigo()).withType("GET"));
		areaComumModel.add(linkTo(methodOn(AreaComumController.class).update(areaComumEntity.getId())).withRel("UPDATE AreaComum " + areaComumEntity.getCodigo()).withType("PUT"));
		areaComumModel.add(linkTo(methodOn(AreaComumController.class).delete(areaComumEntity.getId())).withRel("DELETE AreaComum " + areaComumEntity.getCodigo()).withType("DELETE"));

		return areaComumModel;
	}
	
	public Set<AreaComumModel> toModel(Set<AreaComumEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<AreaComumModel> toModel(List<AreaComumEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
