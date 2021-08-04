package com.Leather.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="movimientos")
public class Movimiento implements Serializable {
	
	// -------- variables de la tabla ------------------- //

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// PostGresql
	private Long id;
	private Long dinero;
	private Long tipo;
	
	private Pedido pedido;
	private BodegaInventario bodegaInventario;
	
		
	// ------------------------ variables de auditoría --------------------- //
	
	@Column(name = "fecha_registro")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.ANY)
	private Date fechaRegistro;
	
	@Column(name="hora_registro")
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.ANY)
	private Date horaRegistro;
	
	@Column(name="fecha_modificacion")
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.ANY)
	private Date fechaModificacion;
	
	@Column(name="hora_modificacion")
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.ANY)
	private Date horaModificacion;
	
	// ----------------------------- GETTERS y SETTERS ----------------------------- //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDinero() {
		return dinero;
	}
	
	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public void setDinero(Long dinero) {
		this.dinero = dinero;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public BodegaInventario getBodegaInventario() {
		return bodegaInventario;
	}

	public void setBodegaInventario(BodegaInventario bodegaInventario) {
		this.bodegaInventario = bodegaInventario;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getHoraModificacion() {
		return horaModificacion;
	}

	public void setHoraModificacion(Date horaModificacion) {
		this.horaModificacion = horaModificacion;
	}




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// --------------- acciones automáticas --------------------------- //
	
	@PrePersist
	public void asignaFechaRegistro(){
		this.fechaRegistro = new Date();
		this.horaRegistro = new Date();
	}
	
	@PreUpdate
	public void asignaFechaModificacion(){
		this.fechaModificacion = new Date();
		this.horaModificacion = new Date();
	}
	
	
}
