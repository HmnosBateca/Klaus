package com.Leather.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Estado")
public class EstadoEnvioCiudad implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fechaRegistro;
	private String fechaEnvio;
	private String entrega;
	private Long numeroGuia;
	private String estadoEnvio;
	private String archivoAdjunto;
	private String observaciones;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"listaEstadoEnvioCiudad","handler", "hibernateLazyInitializer"})
	private Pedido pedido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value= {"handler", "hibernateLazyInitializer"})
	private EnvioCiudad envioCiudad;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getFechaEnvio() {
		return fechaEnvio;
	}
	public void setFechaEnvio(String fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public Long getNumeroGuia() {
		return numeroGuia;
	}
	public void setNumeroGuia(Long numeroGuia) {
		this.numeroGuia = numeroGuia;
	}

	public String getEstadoEnvio() {
		return estadoEnvio;
	}
	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}
	public String getArchivoAdjunto() {
		return archivoAdjunto;
	}
	public void setArchivoAdjunto(String archivoAdjunto) {
		this.archivoAdjunto = archivoAdjunto;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public EnvioCiudad getEnvioCiudad() {
		return envioCiudad;
	}
	public void setEnvioCiudad(EnvioCiudad envioCiudad) {
		this.envioCiudad = envioCiudad;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
