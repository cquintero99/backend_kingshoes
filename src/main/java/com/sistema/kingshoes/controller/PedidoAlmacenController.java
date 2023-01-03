package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.PedidoAlmacen;
import com.sistema.kingshoes.repository.IPedidoAlmacenRepository;

@RestController
@RequestMapping("/pedido/almacen")
public class PedidoAlmacenController {
	@Autowired
	IPedidoAlmacenRepository pedidoAlmacenRepository;

	@GetMapping()
	public List<PedidoAlmacen> lista() {
		return pedidoAlmacenRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<PedidoAlmacen> getById(@PathVariable Integer id) {
		Optional<PedidoAlmacen> pedido = pedidoAlmacenRepository.findById(id);
		if (pedido.isPresent()) {
			return pedido;
		}
		return null;
	}

	@PostMapping("/save")
	public PedidoAlmacen save(@RequestBody PedidoAlmacen pedido) {
		pedidoAlmacenRepository.save(pedido);
		return pedido;
	}

	/*
	 * @PutMapping("/update") public PedidoAlmacen update(@PathVariable Integer
	 * id,@RequestBody PedidoAlmacen pedido) {
	 * 
	 * }
	 */
	@PutMapping
	public PedidoAlmacen update(@PathVariable Integer id, @RequestBody PedidoAlmacen pedido) {
		Optional<PedidoAlmacen>pedidoCurrent=pedidoAlmacenRepository.findById(id);
		if(pedidoCurrent.isPresent()) {
			PedidoAlmacen pedidoReturn =pedidoCurrent.get();
			pedidoReturn.setCantidad(pedido.getCantidad());
			pedidoReturn.setPrecio(pedido.getPrecio());
			pedidoReturn.setEstado(pedido.getEstado());
			
			pedidoAlmacenRepository.save(pedidoReturn);
			return pedidoReturn;
		}
		return null;
	}
	@DeleteMapping("/delete")
	public PedidoAlmacen delete(@PathVariable Integer id) {
		Optional<PedidoAlmacen>pedido=pedidoAlmacenRepository.findById(id);
		if(pedido.isPresent()) {
			pedidoAlmacenRepository.deleteById(id);
			return pedido.get();
		}
		return null;
	}

}
