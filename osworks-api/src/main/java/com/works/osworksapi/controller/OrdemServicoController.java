package com.works.osworksapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.works.osworksapi.api.model.ComentarioInputModel;
import com.works.osworksapi.api.model.ComentarioModel;
import com.works.osworksapi.api.model.OrdemServicoInputModel;
import com.works.osworksapi.api.model.OrdemServicoModel;
import com.works.osworksapi.domain.dao.OrdemServicoRepository;
import com.works.osworksapi.domain.model.Cliente;
import com.works.osworksapi.domain.model.Comentario;
import com.works.osworksapi.domain.model.OrdemServico;
import com.works.osworksapi.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemSrv;
	
	@Autowired
	private OrdemServicoRepository ordemRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrdemServicoModel> criarOrdemServico(@Valid @RequestBody OrdemServicoInputModel ordemSrv) {
		
		return ResponseEntity.ok(this.toModel(this.gestaoOrdemSrv.criarOrdemServico(toEntity(ordemSrv))));
	}
	
	@GetMapping
	public List<OrdemServicoModel> listar() {
		return this.toCollection(this.ordemRepo.findAll());
	}
	
	@GetMapping("/{ordemId}")
	public ResponseEntity<OrdemServicoModel> buscarOrdemServico(@PathVariable Long ordemId) {
		
		Optional<OrdemServico> ordem = this.ordemRepo.findById(ordemId);
		
		if(ordem.isPresent()) {
			
			OrdemServicoModel model = this.toModel(ordem.get());
			
			return ResponseEntity.ok(model);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{ordemId}/finalizacao")
	public ResponseEntity<String> finalizarOrdemServico(@PathVariable Long ordemId) {
		this.gestaoOrdemSrv.finalizarOrdem(ordemId);
		
		return ResponseEntity.ok("Ordem de Serviço finalizada com sucesso.");
	}
	
	@PostMapping("/{ordemId}/comentarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionarComentario(@PathVariable Long ordemId, @Valid @RequestBody ComentarioInputModel comentarioInput) {
		
		return this.toComentarioModel(this.gestaoOrdemSrv.adiconarComentario(ordemId, comentarioInput.getDescricao()));
	}
	
	@GetMapping("/{ordemId}/comentarios")
	public List<ComentarioModel> adicionarComentario(@PathVariable Long ordemId) {
		
		return this.toCollectionComentario(this.gestaoOrdemSrv.buscarTodosComentarios(ordemId));
	}
	
	private OrdemServicoModel toModel(OrdemServico ordem) {
		return  modelMapper.map(ordem, OrdemServicoModel.class);
	}
	
	private List<OrdemServicoModel> toCollection(List<OrdemServico> lista) {
		return lista.stream()
				.map(ordem -> this.toModel(ordem))
				.collect(Collectors.toList());
	}
	
	private OrdemServico toEntity(@Valid OrdemServicoInputModel ordemSrv) {
		
		return new OrdemServico(ordemSrv.getDescricao(), ordemSrv.getPreco(), new Cliente(ordemSrv.getCliente().getId()));
	}
	
	private List<ComentarioModel> toCollectionComentario(List<Comentario> lista) {
		
		return lista.stream()
				.map(comentario -> this.toComentarioModel(comentario))
				.collect(Collectors.toList());
	}
	
	private ComentarioModel toComentarioModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
}
