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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="unidades_medida")
public class UnidadMedida implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// -------- variables de la tabla ------------------- //
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String categoria;
	private String nombre;
	private String abreviatura;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadMedida")
	@JsonIgnoreProperties(value = {"unidadMedida", "handler", "hibernateLazyInitializer"})
	private List<Material> listaMateriales;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadMedida")
	@JsonIgnoreProperties(value = {"unidadMedida", "handler", "hibernateLazyInitializer"})
	private List<GastoMaterialProducto> listaGastoMaterial;

		
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

	
	// -------- Getters y Setters ------------------- //
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}


	
	public List<GastoMaterialProducto> getListaGastoMaterial() {
		return listaGastoMaterial;
	}

	public void setListaGastoMaterial(List<GastoMaterialProducto> listaGastoMaterial) {
		this.listaGastoMaterial = listaGastoMaterial;
	}
	
	public void addGastoMaterial(GastoMaterialProducto gastoMaterial) {
		this.listaGastoMaterial.add(gastoMaterial);
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
	
	
	
	// --------------- acciones automáticas --------------------------- //
	
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
