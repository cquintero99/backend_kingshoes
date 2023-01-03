package com.sistema.kingshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.kingshoes.entities.Pedido;

public interface IPedidoRepository  extends JpaRepository<Pedido, Integer>{

}
