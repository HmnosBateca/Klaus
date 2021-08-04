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
import javax.persistence.OneToOne;
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
		
	private String referencia;
	private Long valorIva;
	private Long valorFinalVenta;
	private String observaciones;
	private Ciudad ciudadEnvio;
	private String direccionEnvio;
	private Long valorEnvio;
	private String nombreUsuario;

	
	@OneToMany(mappedBy ="pedido", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"pedido"})
	private List<EstadoPedido>	listaEstadoPedido;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"pedido","listaPedido", "listaEnvioCiudad", "handler", "hibernateLazyInitializer"})
	private Cliente cliente;
		
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"pedido"}, allowSetters = true)
	private List<Cotizacion> listaCotizacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnoreProperties(value = {"listaPedido", "handler", "hibernateLazyInitializer"} )
	private EnvioCiudad envioCiudad;
	
	
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
		
	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
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

	public Ciudad getCiudadEnvio() {
		return ciudadEnvio;
	}

	public void setCiudadEnvio(Ciudad ciudadEnvio) {
		this.ciudadEnvio = ciudadEnvio;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public Long getValorEnvio() {
		return valorEnvio;
	}

	public void setValorEnvio(Long valorEnvio) {
		this.valorEnvio = valorEnvio;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<EstadoPedido> getListaEstadoPedido() {
		return listaEstadoPedido;
	}

	public void setListaEstadoPedido(List<EstadoPedido> listaEstadoPedido) {
		this.listaEstadoPedido = listaEstadoPedido;
	}
	public void addEstadoPedido(EstadoPedido estadoPedido) {
		this.listaEstadoPedido.add(estadoPedido);
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cotizacion> getListaCotizacion() {
		return listaCotizacion;
	}
	public void setListaCotizacion(List<Cotizacion> listaCotizacion) {
		this.listaCotizacion = listaCotizacion;
	}
	public void addCotizacion(Cotizacion cotizacion) {
		this.listaCotizacion.add(cotizacion);
	}
	
	public EnvioCiudad getEnvioCiudad() {
		return envioCiudad;
	}

	public void setEnvioCiudad(EnvioCiudad envioCiudad) {
		this.envioCiudad = envioCiudad;
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
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
