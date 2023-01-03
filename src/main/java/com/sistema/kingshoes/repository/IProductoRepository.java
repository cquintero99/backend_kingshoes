package com.sistema.kingshoes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.kingshoes.entities.Producto;
@Repository
public interface IProductoRepository  extends JpaRepository<Producto, Integer>{
	
}
