package com.works.osworksapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.works.osworksapi.domain.exception.NegocioException;

@Entity
@Table(name="ORDEMSERVICO")
//@JsonInclude(Include.NON_NULL)
public class OrdemServico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Valid
//	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@ManyToOne
//	@NotNull
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
//	@NotNull
	private BigDecimal preco;

	@Column(name="status", length = 20)
	@Enumerated(EnumType.STRING)
	private StatusOrdemServicoEnum statusEnum;
	
//	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataAbertura;
	
//	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;
	
	@OneToMany(mappedBy = "ordem")
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	/**
	 * 
	 */
	public OrdemServico() {
		super();
	}
	
	public OrdemServico(String descricao, BigDecimal preco, Cliente cliente) {
		this.descricao = descricao;
		this.preco = preco;
		this.cliente = cliente;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	/**
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}
	/**
	 * @param preco the preco to set
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	/**
	 * @return the statusEnum
	 */
	public StatusOrdemServicoEnum getStatusEnum() {
		return statusEnum;
	}
	/**
	 * @param statusEnum the statusEnum to set
	 */
	public void setStatusEnum(StatusOrdemServicoEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	/**
	 * @return the dataAbertura
	 */
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	/**
	 * @param dataAbertura the dataAbertura to set
	 */
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	/**
	 * @return the dataFinalizacao
	 */
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	/**
	 * @param dataFinalizacao the dataFinalizacao to set
	 */
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	/**
	 * @return the comentarios
	 */
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	/**
	 * @param comentarios the comentarios to set
	 */
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void finalizar() {
		
		if(naoPodeSerFinalizadaCancelada()) {
			throw new NegocioException("Ordem de Serviço não pode ser finalizada.");
		}
		
		this.setDataFinalizacao(OffsetDateTime.now());
		this.setStatusEnum(StatusOrdemServicoEnum.FINALIZADA);
	}
	
	private boolean naoPodeSerFinalizadaCancelada() {
		return !StatusOrdemServicoEnum.ABERTA.equals(getStatusEnum());
	}
	
	public void cancelar() {
		
		if(naoPodeSerCancelada()) {
			throw new NegocioException("Ordem de Serviço não pode ser cancelada.");
		}
		
		this.setDataFinalizacao(OffsetDateTime.now());
		this.setStatusEnum(StatusOrdemServicoEnum.CANCELADA);
	}

	private boolean naoPodeSerCancelada() {
		return naoPodeSerFinalizadaCancelada() && !StatusOrdemServicoEnum.FINALIZADA.equals(getStatusEnum());
	}
	
	
	
}
