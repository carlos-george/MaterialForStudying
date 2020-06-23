package com.works.osworksapi.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInputModel {
	
	@Valid
	@NotNull
	private ClienteInputModel cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	/**
	 * @return the cliente
	 */
	public ClienteInputModel getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(ClienteInputModel cliente) {
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
	
}
