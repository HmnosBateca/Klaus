package com.Leather.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;


/*
 	La clase Proveedor registra la información de los proveedores del sistema
*/

@Entity
@Table(name="proveedores")
public class Proveedor implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	
	////////////////////////// variables
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombres;
	@Column(nullable = false)
	private String apellidos;
	private String nit;
	private Long documento;
	
	@Column(name="numero_contacto")
	private Long numeroContacto;
	
	@Column(name="correo_electronico")
	private String correoElectronico;
	
	@Column(name="direccion_residencia")
	private String direccionResidencia;
	
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
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"proveedores", "clientes", "handler", "hibernateLazyInitializer"})
	private Ciudad ciudad;
	
	
	////////////////////////////// Getters y Setters
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getNit() {
		return nit;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public Long getDocumento() {
		return documento;
	}
	
	public void setDocumento(Long documento) {
		this.documento = documento;
	}
	
	public Long getNumeroContacto() {
		return numeroContacto;
	}
	
	public void setNumeroContacto(Long numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getDireccionResidencia() {
		return direccionResidencia;
	}
	
	public void setDireccionResidencia(String direccionResidencia) {
		this.direccionResidencia = direccionResidencia;
	}
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	
	public Date getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(Date horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public Date getHoraModificacion() {
		return horaModificacion;
	}

	public void setHoraModificacion(Date horaModificacion) {
		this.horaModificacion = horaModificacion;
	}
	
	
	public Ciudad getCiudad() {
		return this.ciudad;
	}
	
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	//////// Métodos que se ejecutan antes de realizar persist() /////////////////////
	
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
