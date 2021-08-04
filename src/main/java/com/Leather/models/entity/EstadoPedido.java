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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="estados_pedidos")
public class EstadoPedido implements Serializable {


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//POSTGRESQL
    private Long id;
	
	private String nombre; 
	private String datoAdicional;
	private Boolean finEstado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"listaEstadoPedido", "listaCotizacion"})
	private Pedido pedido;
	
	// ------------------------ variables de auditoría --------------------- //
	
		@Column(name = "fecha_registro")
		@Temporal(TemporalType.DATE)
		@JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.ANY)
		private Date fechaRegistro;
		
		@Column(name="hora_registro")
		@Temporal(TemporalType.TIME)
		@JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.ANY)
		private Date horaRegistro;
		
		// ----------------------------- GETTERS y SETTERS ----------------------------- //
		
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
				
		public String getDatoAdicional() {
			return datoAdicional;
		}

		public void setDatoAdicional(String datoAdicional) {
			this.datoAdicional = datoAdicional;
		}

		public Boolean getFinEstado() {
			return finEstado;
		}

		public void setFinEstado(Boolean finEstado) {
			this.finEstado = finEstado;
		}

		public Pedido getPedido() {
			return pedido;
		}

		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
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
		
		
		// --------------- acciones automáticas --------------------------- //
		
		@PrePersist
		public void asignaFechaRegistro(){
			this.fechaRegistro = new Date();
			this.horaRegistro = new Date();
		}
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		

		
}
