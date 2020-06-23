package com.works.osworksapi.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.works.osworksapi.domain.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

}
