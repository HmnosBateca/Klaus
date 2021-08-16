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


/*
 * Esta clase define las variables para la tabla gasto_material_producto.
 * Esta tabla almacena el valor del material utilizado en la elaboración de un producto para una talla determinada
 * 
 *  @autor: Sergio Bateca
 *  @version: 1.0
 * */




@Entity
@Table(name = "gasto_material_producto")
public class GastoMaterialProducto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// -------------- variables de la tabla------------------------ //
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private Double valor;
	private Double cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"listaGastoMaterialProducto", "producto", "listaBodegaInventario", "horaRegistro", "hibernateLazyInitializer", "handler"})
	private Talla talla;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"listaGastoMaterial", "producto","hibernateLazyInitializer", "handler"}, allowSetters = true)
	private Pieza pieza;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"listaGastoMaterial", "listaCostosMateriales", "horaRegistro", "hibernateLazyInitializer", "handler"})
	private UnidadMedida unidadMedida;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"listaGastoMaterial", "hibernateLazyInitializer", "handler"})
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

		
		// ----------------- getters y setters -------------------------------- //
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Double getValor() {
			return valor;
		}

		public void setValor(Double valor) {
			this.valor = valor;
		}

		public Talla getTalla() {
			return talla;
		}

		public void setTalla(Talla talla) {
			this.talla = talla;
		}
		
		

		public Pieza getPieza() {
			return pieza;
		}

		public void setPieza(Pieza pieza) {
			this.pieza = pieza;
		}
		
		
		

		public Double getCantidad() {
			return cantidad;
		}

		public void setCantidad(Double cantidad) {
			this.cantidad = cantidad;
		}
		
		

		public UnidadMedida getUnidadMedida() {
			return unidadMedida;
		}

		public void setUnidadMedida(UnidadMedida unidadMedida) {
			this.unidadMedida = unidadMedida;
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
		

		public Producto getProducto() {
			return producto;
		}

		public void setProducto(Producto producto) {
			this.producto = producto;
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
