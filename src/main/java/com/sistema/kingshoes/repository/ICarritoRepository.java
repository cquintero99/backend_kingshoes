package com.sistema.kingshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.kingshoes.entities.Carrito;

public interface ICarritoRepository extends JpaRepository<Carrito, Integer> {

}
