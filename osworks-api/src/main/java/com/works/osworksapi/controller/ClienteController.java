package com.works.osworksapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.works.osworksapi.domain.dao.ClienteRepository;
import com.works.osworksapi.domain.model.Cliente;
import com.works.osworksapi.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepo.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		
		Optional<Cliente> cliente = clienteRepo.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Cliente> adicionar(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.ok(this.clienteService.salvar(cliente));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente) {
		
		if(!clienteRepo.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepo.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		
		if(!clienteRepo.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		this.clienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
}