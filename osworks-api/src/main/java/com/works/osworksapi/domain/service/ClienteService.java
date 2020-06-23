package com.works.osworksapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.works.osworksapi.domain.dao.ClienteRepository;
import com.works.osworksapi.domain.exception.NegocioException;
import com.works.osworksapi.domain.model.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente cliExistente = this.clienteRepo.findByEmail(cliente.getEmail());
		
		if(cliExistente != null && !cliExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return this.clienteRepo.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		
		this.clienteRepo.deleteById(clienteId);
	}
	
}
