package com.works.osworksapi.api.model;

import javax.validation.constraints.NotBlank;

public class ComentarioInputModel {
	
	@NotBlank
	private String descricao;

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
	
}
