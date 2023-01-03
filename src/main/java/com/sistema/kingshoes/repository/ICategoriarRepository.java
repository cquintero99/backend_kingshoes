package com.sistema.kingshoes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.kingshoes.entities.Categoria;

public interface ICategoriarRepository extends JpaRepository<Categoria, Integer> {
	public Optional<Categoria> findByDescripcion(String descripcion);
}
