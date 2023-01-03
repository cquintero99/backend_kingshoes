package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.Pedido;
import com.sistema.kingshoes.entities.PedidoAlmacen;
import com.sistema.kingshoes.repository.IPedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin

public class PedidoController  {
	@Autowired
	IPedidoRepository pedidoRepository;
	
	@GetMapping
	public List<Pedido>lista(){
		return pedidoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Pedido> findPedidoById(@PathVariable Integer id){
		Optional<Pedido>pedido=pedidoRepository.findById(id);
		if(pedido.isPresent()) {
			return pedido;
		}
		return null;
	}
	@GetMapping("/{id}/productos")
	public List<PedidoAlmacen> productos(@PathVariable Integer id){
		Optional<Pedido>pedido=pedidoRepository.findById(id);
		if(pedido.isPresent()) {
			return pedido.get().getProductos();
		}
		return null;
	}
	@PostMapping("/save")
	public Pedido postPedido(@RequestBody Pedido pedido) {
		pedidoRepository.save(pedido);
		return pedido;
	}
	@PutMapping("/{id}")
	public Pedido putPedido(@PathVariable Integer id,@RequestBody Pedido pedido) {
		Optional<Pedido>pedidoCurrent=pedidoRepository.findById(id);
		if(pedidoCurrent.isPresent()) {
			Pedido pedidoReturn=pedidoCurrent.get();
			pedidoReturn.setDireccion_id(pedido.getDireccion_id());
			pedidoReturn.setNumero_productos(pedido.getNumero_productos());
			pedidoReturn.setTotal(pedido.getTotal());
			pedidoRepository.save(pedidoReturn);
			return pedidoReturn;
		}
		return null;
	}
	@DeleteMapping("/{id}")
	public Pedido deletePedido(@PathVariable Integer id) {
		Optional<Pedido>pedido=pedidoRepository.findById(id);
		if(pedido.isPresent()) {
			pedidoRepository.deleteById(id);
			return pedido.get();
		}
		return null;
	}

}
