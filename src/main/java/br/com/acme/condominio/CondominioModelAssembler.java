package br.com.acme.condominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.acme.aviso.AvisoModelAssembler;
import br.com.acme.multa.MultaModelAssembler;
import br.com.acme.unidade.UnidadeModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class CondominioModelAssembler extends RepresentationModelAssemblerSupport<CondominioEntity, CondominioModel> {
	
	@Autowired
	private MultaModelAssembler multaModelAssembler;
	@Autowired
	private UnidadeModelAssembler unidadeModelAssembler;
	@Autowired
	private AvisoModelAssembler avisoModelAssembler;

	public CondominioModelAssembler() {
		super(CondominioController.class, CondominioModel.class);
	}

	@Override
	public CondominioModel toModel(CondominioEntity condominioEntity) {
		
		if(condominioEntity == null) {
			return null;
		}
		CondominioModel condominioModel = instantiateModel(condominioEntity);

		condominioModel.setAvisos(this.avisoModelAssembler.toModel(condominioEntity.getAvisos()));
		condominioModel.setEmail(condominioEntity.getEmail());
		condominioModel.setId(condominioEntity.getId());
		condominioModel.setMultasAplicadas(this.multaModelAssembler.toModel(condominioEntity.getMultasAplicadas()));
		condominioModel.setNome(condominioEntity.getNome());
		condominioModel.setTelefone(condominioEntity.getTelefone());
		condominioModel.setUnidades(this.unidadeModelAssembler.toModel(condominioEntity.getUnidades()));
		
		condominioModel.add(linkTo(methodOn(CondominioController.class).getById(condominioEntity.getId())).withRel("GET Condominio " + condominioEntity.getNome()).withType("GET"));
		condominioModel.add(linkTo(methodOn(CondominioController.class).update(condominioEntity.getId())).withRel("UPDATE Condominio " + condominioEntity.getNome()).withType("PUT"));
		condominioModel.add(linkTo(methodOn(CondominioController.class).delete(condominioEntity.getId())).withRel("DELETE Condominio " + condominioEntity.getNome()).withType("DELETE"));

		return condominioModel;
	}
	
	public Set<CondominioModel> toModel(Set<CondominioEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toSet());
	}
	public List<CondominioModel> toModel(List<CondominioEntity> entities) {
		if(entities == null) {
			return null;
		}
		return entities.stream().map(entity -> this.toModel(entity)).collect(Collectors.toList());
	}

}
