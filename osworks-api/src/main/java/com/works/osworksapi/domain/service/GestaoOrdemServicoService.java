package com.works.osworksapi.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.works.osworksapi.api.exceptionhandler.EntidadeNaoEncontradaException;
import com.works.osworksapi.domain.dao.ClienteRepository;
import com.works.osworksapi.domain.dao.ComentarioRepository;
import com.works.osworksapi.domain.dao.OrdemServicoRepository;
import com.works.osworksapi.domain.exception.NegocioException;
import com.works.osworksapi.domain.model.Cliente;
import com.works.osworksapi.domain.model.Comentario;
import com.works.osworksapi.domain.model.OrdemServico;
import com.works.osworksapi.domain.model.StatusOrdemServicoEnum;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private ComentarioRepository comentarioRepo;
	
	public OrdemServico criarOrdemServico(OrdemServico ordemServico) {
		
		Cliente cliente = clienteRepo.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatusEnum(StatusOrdemServicoEnum.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return this.ordemRepo.save(ordemServico);
	}
	
	public void finalizarOrdem(Long ordemId) {
		
		OrdemServico ordem = obterOrdemServico(ordemId);
		
		ordem.finalizar();
		
		this.ordemRepo.save(ordem);
	}

	private OrdemServico obterOrdemServico(Long ordemId) {
		OrdemServico ordem = this.ordemRepo.findById(ordemId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de Serviço não encontrada"));
		return ordem;
	}
	
	public Comentario adiconarComentario(Long ordemId, String descricao) {
		
		OrdemServico ordem = obterOrdemServico(ordemId);
		
		Comentario c = new Comentario();
		
		c.setDataEnvio(OffsetDateTime.now());
		
		c.setDescricao(descricao);
		
		c.setOrdem(ordem);
		
		this.comentarioRepo.save(c);
		
		return this.comentarioRepo.save(c);
	}

	public List<Comentario> buscarTodosComentarios(Long ordemId) {
		
		OrdemServico ordem = obterOrdemServico(ordemId);
		
		return ordem.getComentarios();
	}
}
