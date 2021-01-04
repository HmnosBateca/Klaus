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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Table(name = "piezas")
@Entity
public class Pieza implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// --------------- campos de la tabla -------------------- //
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "nombre_pieza")
	private String nombrePieza;
	private String observacion;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"piezas", "handler", "hibernateLazyInitializer"})
	private Color color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"materiales", "piezas", "handler", "hibernateLazyInitializer"})
	private Material material;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"piezas", "listaBodegaInventario", "handler", "hibernateLazyInitializer"}, allowSetters = true)
	private Producto producto;
	
	
	
	// ---------------------- campos de auditoría --------------------- //


	@Column(name="fecha_registro")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern ="yyyy-MM-dd")
	private Date fechaRegistro;
	
	@Column(name="hora_registro")
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern ="HH:mm:ss")
	private Date horaRegistro;
	
	@Column(name="fecha_modificacion")
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern ="yyyy-MM-dd")
	private Date fechaModificacion;
	
	@Column(name="hora_modificacion")
	@Temporal(TemporalType.TIME)
	@JsonFormat(shape = JsonFormat.Shape.ANY, pattern = "HH:mm:ss")
	private Date horaModificacion;
	
	
	
	// ------------------------ GETTERS Y SETTERS ---------------------- //
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePieza() {
		return nombrePieza;
	}

	public void setNombrePieza(String nombrePieza) {
		this.nombrePieza = nombrePieza;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	
	
	
	
	// Al registrar se instancia automáticamente la fecha de registro del proveedor
	@PrePersist
	void asignaFechaRegistro() {
		this.fechaRegistro = new Date();
		this.horaRegistro = new Date();
	}
	
	// Al realizar una operación de actualización se ejecuta automáticamente este método
	@PreUpdate
	void registrarFechaActualizacion() {
		this.fechaModificacion = new Date();
		this.horaModificacion = new Date();
	}
	
	
	
	

}
