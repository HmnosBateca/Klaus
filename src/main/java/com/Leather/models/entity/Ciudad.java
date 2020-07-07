package com.Leather.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
//@Table(name="ciudades")
public class Ciudad implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//IDENTITY auto incrementar
	private Long id; // ciudad_id
	
	private String nombre;
	//private int departamento_id;
	
	//fetch optener los datos de la operacion lazy perezosa, realiza la consulta cuando se le llama,
	//muchas ciudades un departamento
	@ManyToOne(fetch=FetchType.LAZY) 
	@JsonIgnoreProperties(value = {"ciudades","handler", "hibernateLazyInitializer"}) 
	private Departamento departamento;
	
	
	/*
	 * Cuando se establecen relaciones bidireccionales entre clases se debe usar el "mappedBy"
	 * y allí se indicará el nombre del campo de la otra clase que será mapeado en la relación.
	 * Importante: 
	 * 				- En relaciones ManyToOne o OneToMany el mappedBy se agrega en el One de la relación
	 * 				- En relaciones OneToOne el mappedBy se agrega en la clase fuerte (o que manda)
	*/
	
	
	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY )
	@JsonIgnoreProperties(value = {"ciudad","handler", "hibernateLazyInitializer"})
	private List<Cliente> clientes;
	
	
	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"ciudad", "handler", "hibernateLazyInitializer"})
	private List<Proveedor> proveedores;
	
	
	public Ciudad() {
		clientes= new ArrayList<Cliente>();
		proveedores = new ArrayList<Proveedor>();
	}
	
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


	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
    
	//Agrego cliente por cliente en ciudad
	public void addCliente(Cliente cliente) {
		clientes.add(cliente);
	}


	
	public List<Proveedor> getProveedores(){
		return this.proveedores;
	}
	
	
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	
	public void addProveedor(Proveedor proveedor) {
		this.proveedores.add(proveedor);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
