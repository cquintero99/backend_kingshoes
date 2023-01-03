package com.sistema.kingshoes.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="pedido_almacen")
public class PedidoAlmacen implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer pedido_id;
	private Integer almacen_id;
	private Integer cantidad;
	private Integer precio;
	private Integer tienda_id;
	private String estado;

}
