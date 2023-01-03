package com.sistema.kingshoes.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer usuario_id;
	
	private Integer direccion_id;
	private Integer numero_productos;
	private Integer total;
	private String fecha;
	@Column(name="metodo_pago")
	private String metodoPago;
	@JsonIgnore
	@OneToMany(mappedBy = "pedido_id")
	private List<PedidoAlmacen>productos;

}
