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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;





@Entity
@Table(name = "tipo_tallas")
public class TipoTalla implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// ----------------- variables ------------------------ //
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, name="tipo_talla")
	private String tipoTalla;
	
	private String descripcion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy ="tipoTalla")
	@JsonIgnoreProperties(value = {"tipoTalla" ,"handler", "hibernateLazyInitializer"})
	private List<Talla> tallas; 
	
	
	
	// ----------------- variables de auditoria ------------------------ //
	

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

	

	// --------------------------- getters y setters ----------------------------- //
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoTalla() {
		return tipoTalla;
	}

	public void setTipoTalla(String tipoTalla) {
		this.tipoTalla = tipoTalla;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public List<Talla> getTallas() {
		return tallas;
	}

	public void setTallas(List<Talla> tallas) {
		this.tallas = tallas;
	}
	
	public void addTalla(Talla talla) {
		this.tallas.add(talla);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	// ------------------- acciones automáticas ---------------------------- //
	
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
