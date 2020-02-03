/**
 * 
 */
package br.com.acme.condominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.acme.aviso.AvisoEntity;
import br.com.acme.multa.MultaEntity;
import br.com.acme.unidade.UnidadeEntity;
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
@Table(name = "tb_condominio")
public class CondominioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String email;
	
	private String telefone;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condominoMultado")
	private Set<MultaEntity> multasAplicadas;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condominioUnidade")
	private Set<UnidadeEntity> unidades;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "condominioAviso")
	private Set<AvisoEntity> avisos;
	
}
