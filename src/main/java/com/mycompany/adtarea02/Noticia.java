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
public class Noticia {
    String texto;
    //Dejamos el constructor por defecto.
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "************************************************************************************************\nNoticia:\n" + texto 
                + "\n************************************************************************************************\n";
    }
    
    
}
