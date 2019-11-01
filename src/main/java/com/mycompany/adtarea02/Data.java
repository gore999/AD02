/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class Data {
    //Archivo para guardar los datos. 
    static final File archivo=new File("datos.dat");
    //atributos de la clase Data.
    ArrayList <Tienda> tiendasLista=new ArrayList();
    ArrayList <Cliente> clientesLista=new ArrayList();
    //CONSTRUCTORES
    public Data(){}//Constructor vacio.
    public Data(ArrayList<Tienda> tiendas, ArrayList<Cliente> clientes) {
        this.clientesLista=clientes;
        this.tiendasLista=tiendas;
    }
    //GETTERS y SETTERS
    public ArrayList<Tienda> getTiendasLista() {
        return tiendasLista;
    }

    public void setTiendasLista(ArrayList<Tienda> tiendasLista) {
        this.tiendasLista = tiendasLista;
    }

    public ArrayList<Cliente> getClientesLista() {
        return clientesLista;
    }

    public void setClientesLista(ArrayList<Cliente> clientesLista) {
        this.clientesLista = clientesLista;
    }
    
    //METODOS PROPIOS
    public void saveData(File file){
        System.out.println("Guardando datos...");
        FileWriter fw=null;
        Data dAux=new Data(this.tiendasLista,this.clientesLista);
        System.out.println("el nuevo data es..."+dAux.toString());
        try {
            fw=new FileWriter(file);
            Gson g=new Gson();
            g.toJson(dAux,fw);
        } catch (IOException ex) {
            System.out.println("Error al escribir el archivo");
        }finally{
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 
    @Override
    public String toString() {
        return "Data{" + "tiendasLista=" + tiendasLista + ", clientesLista=" + clientesLista + '}';
    }

   

}
