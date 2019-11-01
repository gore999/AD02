/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

/**
 *
 * @author Carlos
 */
public class Empleado {
    private String nombre;
    private String apellidos;

    public Empleado(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", apellidos=" + apellidos + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Empleado emp=(Empleado)obj; //To change body of generated methods, choose Tools | Templates.
        if(emp.nombre.equals(this.nombre) && emp.apellidos.equals(this.apellidos)){
            return true;
        }else{
            return false;
        }
    }
    
}
