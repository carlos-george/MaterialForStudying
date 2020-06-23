package com.works.osworksapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.works.osworksapi.domain.model.StatusOrdemServicoEnum;

@JsonInclude(Include.NON_NULL)
public class OrdemServicoModel {

	private Long id;
	private ClienteModel cliente;
	private String emailCliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServicoEnum status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	
	/**
	 * 
	 */
	public OrdemServicoModel() {
		super();
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
	public ClienteModel getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the emailCliente
	 */
	public String getEmailCliente() {
		return emailCliente;
	}

	/**
	 * @param emailCliente the emailCliente to set
	 */
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
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
	 * @return the status
	 */
	public StatusOrdemServicoEnum getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusOrdemServicoEnum status) {
		this.status = status;
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
	
}
