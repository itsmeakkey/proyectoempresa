package com.proyecto.empresa.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity //Cada campo añadido es una columna en BBDD
@Table
public class Tareas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre_tarea;
    private Date fecha_creacion;
    private Date fecha_fin;
    private Boolean entregado_a_tiempo;
    private Date fecha_estimada;
    
    //Relación muchos a uno con empleado
    @ManyToOne
    @JoinColumn (name = "empleado_id") //Clave foránea
    private Empleado empleado;
    
    //Getters y setters 
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre_tarea() {
		return nombre_tarea;
	}
	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}
	public Date getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public Date getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	public Boolean getEntregado_a_tiempo() {
		return entregado_a_tiempo;
	}
	public void setEntregado_a_tiempo(Boolean entregado_a_tiempo) {
		this.entregado_a_tiempo = entregado_a_tiempo;
	}
	public Date getFecha_estimada() {
		return fecha_estimada;
	}
	public void setFecha_estimada(Date fecha_estimada) {
		this.fecha_estimada = fecha_estimada;
	}
}
 