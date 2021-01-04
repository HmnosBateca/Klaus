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
@Table(name = "tallas")
public class Talla implements Serializable{

	private static final long serialVersionUID = 1L;
	
	// ---------------------- variables ----------------------- //
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String talla;
	
	private String descripcion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"tallas", "handler", "hibernateLazyInitializer"})
	private TipoTalla tipoTalla;
	
	/*@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
	private List<ReferenciaProducto> listaReferenciaProducto;*/
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
	private List<BodegaInventario> listaBodegaInventario;

	// ----------------------------- variables de auditoría --------------------//
	
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

	
	// ------------------ GETTERS Y SETTERS -------------------------- //
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
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
	
	public TipoTalla getTipoTalla() {
		return tipoTalla;
	}

	public void setTipoTalla(TipoTalla tipoTalla) {
		this.tipoTalla = tipoTalla;
	}	
	
	
	public List<BodegaInventario> getListaBodegaInventario() {
		return listaBodegaInventario;
	}

	public void setListaBodegaInventario(List<BodegaInventario> listaBodegaInventario) {
		this.listaBodegaInventario = listaBodegaInventario;
	}

	public void addBodegaInventario(BodegaInventario bodegaInventario) {
		this.listaBodegaInventario.add(bodegaInventario);
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