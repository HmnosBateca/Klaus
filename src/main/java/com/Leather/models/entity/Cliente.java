package com.Leather.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
// import javax.persistence.Table;
import javax.persistence.OneToMany;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@Table(name="clientes")
public class Cliente implements Serializable{//seralizable atribustos de la tabla

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)//POSTGRESQL
    private Long id;
	
	@Column(unique = true)
	private Long documento;
	private String nombres;
	private String apellidos;
	private Long numero_contacto;
	private String direccion;
	private String correo;
	private Long codigo_postal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"clientes","proveedores","handler", "hibernateLazyInitializer"})
	private Ciudad ciudad;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY )
	@JsonIgnoreProperties(value = {"cliente", "listaPedido", "handler", "hibernateLazyInitializer"})
	private List<Pedido> listaPedido;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDocumento() {
		return documento;
	}
	public void setDocumento(Long documento) {
		this.documento = documento;
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
	public Long getNumero_contacto() {
		return numero_contacto;
	}
	public void setNumero_contacto(Long numero_contacto) {
		this.numero_contacto = numero_contacto;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Long getCodigo_postal() {
		return codigo_postal;
	}
	
	public void setCodigo_postal(Long codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
		
	public Ciudad getCiudad() {
		return this.ciudad;
	}
	
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	public List<Pedido> getListaPedido() {
		return listaPedido;
	}
	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}
	public void addPedido(Pedido pedido) {
		this.listaPedido.add(pedido);
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
