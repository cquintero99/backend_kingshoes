package com.sistema.kingshoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.kingshoes.entities.Tienda;
@Repository
public interface ITiendaRepository  extends JpaRepository<Tienda,Integer >{
public Optional<Tienda > findByIdUsuario(Integer idUsuario);
}
