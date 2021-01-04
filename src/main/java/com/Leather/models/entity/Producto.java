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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// -------- variables de la tabla ------------------- //

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@Column(nullable = false)
	private String referencia;
	
	private Double costo;
	
	@Column(name = "precio_venta")
	private Double precioVenta;
	
	private boolean activo = false;
	
	
	@Lob // una anotación para almacenar grandes cadenas de Bytes. Se puede almacednar cualquier tipo de archivo
	@JsonIgnore // para que no salga en el JSON los binarios, pues son muchos
	private byte[] foto;
	
	private String nombreFoto;
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
	@JsonIgnoreProperties(value = {"producto", "handler", "hibernateLazyInitializer"}, allowSetters = true)
	private List<Pieza> piezas;
		
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"producto"})
	private List<BodegaInventario> listaBodegaInventario;
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		this.precioVenta = precioVenta;
	}
	

	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public Integer getFotoHashCode() {
		return (this.foto != null) ?this.foto.hashCode(): null;
	}
	
	

	public String getNombreFoto() {
		return nombreFoto;
	}

	public void setNombreFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}

	public List<Pieza> getPiezas() {
		return piezas;
	}
	public void setPiezas(List<Pieza> piezas) {
		this.piezas = piezas;
	}
	public void addPieza(Pieza pieza) {
		this.piezas.add(pieza);
	}
	public List<BodegaInventario> getListaBodegaInventario() {
		return listaBodegaInventario;
	}
	public void setListaBodegaInventario(List<BodegaInventario> listaBodegaInventario) {
		this.listaBodegaInventario = listaBodegaInventario;
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
