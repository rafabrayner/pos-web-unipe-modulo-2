package br.com.acme.reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.acme.areacomum.AreaComumModel;
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
@JsonRootName(value = "reserva")
@Relation(collectionRelation = "reservas")
@JsonInclude(Include.NON_NULL)
public class ReservaModel extends RepresentationModel<ReservaModel> {
	
private Long id;
	
	private LocalDate dataReserva;
	private LocalDateTime inicioReserva;
	private LocalDateTime fimReserva;
	private ResponsavelModel responsavelReserva;
	private AreaComumModel areaComumReservada;

}
