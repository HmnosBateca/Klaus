package com.Leather.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cotizaciones")
public class Cotizacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// -------- variables de la tabla ------------------- //
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long refCotizacion;
	private Long cantidad;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
	private BodegaInventario bodegaInventario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
	private Pedido pedido;

	// ----------------------------- GETTERS y SETTERS ----------------------------- //

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRefCotizacion() {
		return refCotizacion;
	}
	public void setRefCotizacion(Long refCotizacion) {
		this.refCotizacion = refCotizacion;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public BodegaInventario getBodegaInventario() {
		return bodegaInventario;
	}
	public void setBodegaInventario(BodegaInventario bodegaInventario) {
		this.bodegaInventario = bodegaInventario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
