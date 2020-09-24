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
@Table(name= "pedidos")
public class Pedido implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// PostGresql
	private Long id; 
	
	@Temporal(TemporalType.DATE)
	// @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date fechaPedido;
	
	@Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "h:mm a", shape = JsonFormat.Shape.ANY, timezone="America/New_York")
	private Date horaPedido;
	
	
	private Long valorIva;
	private Long valorFinalVenta;
	private String observaciones;
		
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"pedido","listaPedido", "listaEnvioCiudad", "handler", "hibernateLazyInitializer"})
	private Cliente cliente;
		
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"pedido","handler", "hibernateLazyInitializer"})
	private List<BodegaInventario> listaBodegaInventario;
	
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
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public Date getHoraPedido() {
		return horaPedido;
	}
	public void setHoraPedido(Date horaPedido) {
		this.horaPedido = horaPedido;
	}
	public Long getValorIva() {
		return valorIva;
	}
	public void setValorIva(Long valorIva) {
		this.valorIva = valorIva;
	}
	public Long getValorFinalVenta() {
		return valorFinalVenta;
	}
	public void setValorFinalVenta(Long valorFinalVenta) {
		this.valorFinalVenta = valorFinalVenta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	
	/*
	public List<EstadoEnvioCiudad> getListaEstadoEnvioCiudad() {
		return listaEstadoEnvioCiudad;
	}
	public void setListaEstadoEnvioCiudad(List<EstadoEnvioCiudad> listaEstadoEnvioCiudad) {
		this.listaEstadoEnvioCiudad = listaEstadoEnvioCiudad;
	}
	public void addEstadoEnvioCiudad(EstadoEnvioCiudad estadoEnvioCiudad) {
		this.listaEstadoEnvioCiudad.add(estadoEnvioCiudad);
	}*/


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
