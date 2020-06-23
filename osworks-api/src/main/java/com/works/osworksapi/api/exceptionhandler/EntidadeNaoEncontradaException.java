package com.works.osworksapi.api.exceptionhandler;

import com.works.osworksapi.domain.exception.NegocioException;

public class EntidadeNaoEncontradaException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}
}
