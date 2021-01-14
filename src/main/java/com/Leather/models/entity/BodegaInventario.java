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
@Table(name="bodegaInventarios") 
public class BodegaInventario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// -------- variables de la tabla ------------------- //

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String referencia;
	private Long cantidad;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"listaBodegaInventario", "handler", "hibernateLazyInitializer"})
	private Producto producto; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value={"listaBodegaInventario", "handler",  "hibernateLazyInitializer"})
	private Talla talla;
			
	@JsonIgnoreProperties(value = {"listaBodegaInventario", "cotizacion", "listaBodegaInventario", "handler", "hibernateLazyInitializer"})
	@OneToMany(fetch = FetchType.LAZY)
	private List<Cotizacion> listaCotizacion;
		
	// ------------------------ variables de auditoría --------------------- //
	
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

		// ----------------------------- GETTERS y SETTERS ----------------------------- //
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getReferencia() {
			return referencia;
		}
		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}
		public Long getCantidad() {
			return cantidad;
		}
		public void setCantidad(Long cantidad) {
			this.cantidad = cantidad;
		}
		public Producto getProducto() {
			return producto;
		}

		public void setProducto(Producto producto) {
			this.producto = producto;
		}
		public Talla getTalla() {
			return talla;
		}
		public void setTalla(Talla talla) {
			this.talla = talla;
		}
		
		public List<Cotizacion> getListaCotizacion() {
			return listaCotizacion;
		}
		public void setListaCotizacion(List<Cotizacion> listaCotizacion) {
			this.listaCotizacion = listaCotizacion;
		}
		public void addListaCotizacion(Cotizacion cotizacion) {
			this.listaCotizacion.add(cotizacion);
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