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
	@JsonIgnore//no se haga un bucle infinito 
	private Departamento departamento;
	
	@OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JsonIgnore
	private List<Cliente> clientes;
	
	public Ciudad() {
		clientes= new ArrayList<Cliente>();
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


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
