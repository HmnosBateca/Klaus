package com.Leather.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="departamento")
public class Departamento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@Column(name="hora_registro")
	@Temporal(TemporalType.TIME)
	private Date horaRegistro;
	
	@Column(name="fecha_modificacion")
	@Temporal(TemporalType.DATE)
	private Date fechaModificacion;
	
	@Column(name ="hora_modificacion")
	@Temporal(TemporalType.TIME)
	private Date horaModificacion;
	
	
	@Column( name = "fecha_registro")
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	///Getters y Setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public void setHoraRegisto(Date horaRegisto) {
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
	
	//Metodos que se ejecuntan antes de realizar persist()///////////////////
	
	//Al regisrar, se instancia automaticamente este metodo
	
	@PrePersist
	void asignaFecharegistro() {
		this.fechaRegistro = new Date();
		this.horaRegistro =new Date();
	}
	
	// Al realizar una operacion de actualizacion se ejecuta automaticamnte este metodo
	void registrarFechaActualizacion() {
		this.fechaModificacion= new Date();
		this.horaModificacion= new Date();
	}
}



