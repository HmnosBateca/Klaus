package com.Leather.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "costos_materiales", uniqueConstraints = @UniqueConstraint(columnNames = {"material_id"}))
public class CostoMaterial implements Serializable{

	/**
	 * Esta tabla almacenará el costo de un determinado material por la unidad de medida determinada por el usuario
	 */
	private static final long serialVersionUID = 1L;
	
	//--------------------- Variables de la tabla ----------------------------//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double cantidad;
	private Double costo;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"listaCostosMateriales", "piezas","handler", "hibernateLazyInitializer"})
	private Material material;
	
	
	
	// -------- variables de auditoría ------------------- //
	
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

	
	// ---------------- getters y setters -------------------------------- //
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	
	


	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	// -------------- acciones automáticas ----------------------------- //
	

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
