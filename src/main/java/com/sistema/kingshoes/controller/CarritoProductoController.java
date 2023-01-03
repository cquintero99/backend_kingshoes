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

import com.sistema.kingshoes.entities.CarritoProducto;
import com.sistema.kingshoes.repository.ICarritoProductoRepository;

@RestController
@RequestMapping("/carrito/productos")
@CrossOrigin
public class CarritoProductoController {
	@Autowired
	ICarritoProductoRepository carritoProductoRepository;

	@GetMapping
	public List<CarritoProducto> listar() {
		return carritoProductoRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<CarritoProducto> findByIdCarritoProducto(@PathVariable Integer id) {
		Optional<CarritoProducto> cp = carritoProductoRepository.findById(id);
		if (cp.isPresent()) {
			return cp;
		}
		return null;
	}

	@PostMapping
	public CarritoProducto save(@RequestBody CarritoProducto carritoProducto) {

		if (carritoProducto.getCarrito() != 0 || carritoProducto.getCarrito() != null) {
			CarritoProducto c = carritoProductoRepository.save(carritoProducto);
			return carritoProducto;
		}
		return null;
	}

	@PutMapping("/{id}")
	public CarritoProducto put(@RequestBody CarritoProducto carritoProducto, @PathVariable Integer id) {

		Optional<CarritoProducto> cpCurrent = carritoProductoRepository.findById(id);
		if (cpCurrent.isPresent()) {
			CarritoProducto cpReturn = cpCurrent.get();
			cpReturn.setCantidad(carritoProducto.getCantidad());
			cpReturn.setTotal(carritoProducto.getTotal());
			carritoProductoRepository.save(cpReturn);
			return cpReturn;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public CarritoProducto delete(@PathVariable Integer id) {
		Optional<CarritoProducto> cp = carritoProductoRepository.findById(id);
		if (cp.isPresent()) {
			carritoProductoRepository.deleteById(id);
			return cp.get();
		}
		return null;
	}

}
