package com.sistema.kingshoes.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="carrito_producto")
public class CarritoProducto  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="id_carrito")
	private Integer carrito;
	@ManyToOne
	@JoinColumn(name="id_almacen")
	private Almacen almacen;
	private Integer cantidad;
	private Integer total;
	private String ref;

}
