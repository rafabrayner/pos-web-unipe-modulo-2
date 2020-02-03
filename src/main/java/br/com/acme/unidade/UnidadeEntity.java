/**
 * 
 */
package br.com.acme.unidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.acme.condominio.CondominioEntity;
import br.com.acme.multa.MultaEntity;
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
@Table(name = "tb_unidade")
public class UnidadeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_responsavel")
	private ResponsavelEntity responsavelUnidade;
	
	private String numeroUnidade;
	
	private String blocoUnidade;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "unidadeMultada")
	private Set<MultaEntity> multasUnidade;
	
	@ManyToOne
	@JoinColumn(name = "id_condominio")
	private CondominioEntity condominioUnidade;
	
}
