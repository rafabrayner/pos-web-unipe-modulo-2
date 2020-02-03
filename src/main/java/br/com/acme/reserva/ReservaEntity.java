/**
 * 
 */
package br.com.acme.reserva;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.acme.areacomum.AreaComumEntity;
import br.com.acme.responsavel.ResponsavelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosfilho
 *
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_reserva")
public class ReservaEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private LocalDate dataReserva;
	
	private LocalDateTime inicioReserva;
	
	private LocalDateTime fimReserva;
	
	@ManyToOne
	@JoinColumn(name = "id_responsavel")
	private ResponsavelEntity responsavelReserva;
	
	@ManyToOne
	@JoinColumn(name = "id_area_comum")
	private AreaComumEntity areaComumReservada;
}
