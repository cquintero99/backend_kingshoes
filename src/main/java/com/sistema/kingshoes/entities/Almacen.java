package com.sistema.kingshoes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Almacen {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="id_producto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name="id_talla")
	private Talla  talla;
	private Integer stock;
	private String ref;

}
