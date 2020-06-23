package com.works.osworksapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.works.osworksapi.api.exceptionhandler.Problema.Campo;
import com.works.osworksapi.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		Integer status = HttpStatus.BAD_REQUEST.value();
		Problema p = new Problema();
		
		p.setStatus(status);
		p.setTitulo(ex.getMessage());
		p.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, p, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		Integer status = HttpStatus.NOT_FOUND.value();
		Problema p = new Problema();
		
		p.setStatus(status);
		p.setTitulo(ex.getMessage());
		p.setDataHora(OffsetDateTime.now());
		
		return super.handleExceptionInternal(ex, p, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Campo> campos = new ArrayList<Campo>();
		
		for(ObjectError obj : ex.getBindingResult().getAllErrors()) {
			
			String msg = messageSource.getMessage(obj, LocaleContextHolder.getLocale());
			
			String nome = ((FieldError) obj).getField();
			
			campos.add(new Campo(nome, msg));
		}
		
		Problema p = new Problema();
		
		p.setStatus(status.value());
		p.setTitulo("Um ou mais campos estão inválidos."
				+ " Faça o preenchimento e tente novamente.");
		p.setDataHora(OffsetDateTime.now());
		
		p.setCampos(campos);
		
		return super.handleExceptionInternal(ex, p, headers, status, request);
	}

}
