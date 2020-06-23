package com.works.osworksapi.api.model;

import javax.validation.constraints.NotNull;

public class ClienteInputModel {
	
	@NotNull
	private Long id;

	/**
	 * @return the clienteId
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param clienteId the clienteId to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
}
