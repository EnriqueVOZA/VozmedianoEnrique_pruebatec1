package com.proyectojb.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empleados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private double salario;
    private String fechaInicio;

    public Empleados() {
    }

    public Empleados(Long id, String nombre, String apellido, String cargo, double salario, String fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = this.fechaInicio;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return  "id: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Apellido: " + apellido + "\n" +
                "Cargo:" + cargo + "\n" +
                "Salario: " + salario + "\n" +
                "Fecha de inicio: " + fechaInicio;
    }

    
    
  
}
