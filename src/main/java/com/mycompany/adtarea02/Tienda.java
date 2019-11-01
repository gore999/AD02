/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Tienda {
    private String nombre;
    private String ciudad;
    private ArrayList <Producto> productos;
    private ArrayList <Empleado> empleados;
    public Tienda(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        empleados=new ArrayList();
        productos=new ArrayList();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
    public void insertProducto(Producto p){
        this.productos.add(p);
    }
    public void deleteProducto(Producto p){
        this.productos.remove(p);
    }
    public Producto searchProducto(int identificador){
        Producto pAux=null;
        for (Producto p:productos){
            if(p.getIdentificador()==identificador){
                pAux=p;
                break;//rompo el bucle si encuentro una coincidencia.
            }
        }
        return pAux;
    }
    public Empleado searchEmpregado(String nombre, String Apellido){
        Empleado eAux=null;
        for (Empleado e:empleados){
            if(e.getNombre()==nombre && e.getApellidos()==Apellido){
                eAux=e;
                break;
            }
        }
        return eAux;
    }

    @Override
    public String toString() {
        return "Tenda{" + "nombre=" + nombre + ", ciudad=" + ciudad + ", productos=" + productos + ", empleados=" + empleados + '}';
    }

    @Override
    public boolean equals(Object obj) {
        Tienda t=(Tienda)obj;
       if(this.nombre.equals(t.nombre)&& this.ciudad.equals(t.ciudad)){
           return true;
       }else{
           return false;
       }        
    }
    
}
