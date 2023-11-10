package com.proyectojb.persistencia;

import com.proyectojb.logica.Empleados;
import com.proyectojb.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    EmpleadosJpaController empleadoJPA = new EmpleadosJpaController();
    
    public void crearEmpelado(Empleados emple){
        empleadoJPA.create(emple);
    }
    
    public void eliminarEmpleado(Long id) {
        try {
            empleadoJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public Empleados buscarEmpleado(Long id){
        return empleadoJPA.findEmpleados(id);
    }

    public List<Empleados> traerEmplados(){
        return empleadoJPA.listaEmpleados();
    }
    
    public void modificarEmpleado(Empleados emple){
        try {
            empleadoJPA.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Empleados> buscarEmpleadoPorCargo(String cargo){
        return  empleadoJPA.bucarEmpleadosCargo(cargo);
    }



}
