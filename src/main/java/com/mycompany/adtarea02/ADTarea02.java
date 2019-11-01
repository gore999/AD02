/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

import com.google.gson.Gson;
import com.sun.jndi.toolkit.url.Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class ADTarea02 {
    /**
     * @param args the command line arguments
     */
       private static final String elPaisDir="http://ep00.epimg.net/rss/elpais/portada.xml";

    //METODO MAIN. Crea un Data, recupera datos del archivo y lanza un menu.
    public static void main(String[] args) {
        //Iniciamos un objeto tipo Data
       Data data=new Data();
       Data aux=ADTarea02.recoverData();
       if(aux!=null){data=aux;}
       System.out.println(data.toString());
       menu(data);

    }
   
   //Recuperar los datos del archivo si exite
       public static Data recoverData() {
        //Cargamos datos Json y lo incorporamos a la clase.
        Gson g=new Gson();
        Data dataAux=null;
        FileReader fr=null;
        BufferedReader br=null;
       
        try {
            fr=new FileReader(Data.archivo);
            br=new BufferedReader(fr);
            String json=br.readLine();
            dataAux=g.fromJson(json, Data.class);
           
        } catch (FileNotFoundException ex) {System.out.println("No existe archivo");
        } catch (IOException ex) {System.out.println("Error IO");
        } finally{//Cerramos los streams.
            try {
                br.close();
                fr.close();
            }
            //Creamos un objeto tipo Data desde el json.
            //Asignamos a los atributos del objeto los del objeto auxiliar.
            catch (IOException ex) {
                System.out.println("Error IO");
            }catch(Exception ex){}
        }
        return dataAux;
    }
      //METODO menu
    public static void menu(Data data){
         int eleccion;
         boolean salir=false;
         Tienda t=null;
         while(!salir){
            System.out.println("Elija una opcion\n"
                     + "1.- Añadir Tienda\n"
                     + "2.- Eliminar Tienda\n"
                     + "3.- Añadir producto a Tienda\n"
                     + "4.- Eliminar producto de Tienda\n"
                     + "5.- Añadir empleado a Tienda\n"
                     + "6.- Eliminar empleado de Tienda\n"
                     + "7.- Añadir cliente\n"
                     + "8.- Eliminar cliente\n"
                     + "9.- Crear copia de seguridad\n"
                     + "10.- Ver titulares de El Pais\n"
                    + "0.- Salir");
            eleccion = Integer.parseInt(readString("Elija una opcion;"));
            switch(eleccion){
                case 1:
                    addTienda(data);
                    break;
                case 2:
                    System.out.println("Borradas "+borraTienda(data)+" tiendas.");
                    break;
                case 3:
                    t=searchTienda(data);//Recuperamos la tienda
                    if(t!=null){//si obtenemos una tienda...
                        addProducto(t);// Añadimos un producto.
                    }else{
                       System.out.println("La tienda no existe.");//Mensaje de que la tienda no existe.
                    }
                        break;
                case 4:
                    t=searchTienda(data);//Recuperamos la tienda
                    if(t!=null){//si obtenemos una tienda...
                        System.out.println("Borrados "+deleteProducto(t)+" productos..");
                    }else{
                       System.out.println("La tienda no existe.");//Mensaje de que la tienda no existe.
                    }
                    break;
                case 5:
                    t=searchTienda(data);
                    if(t!=null){//si obtenemos una tienda...
                        addEmpleado(t);// Añadimos un producto.
                    }else{
                       System.out.println("La tienda no existe.");//Mensaje de que la tienda no existe.
                    }
                    break;
                case 6:
                    t=searchTienda(data);
                    if(t!=null){//si obtenemos una tienda...
                        System.out.println("Borrados "+deleteEmpleado(t)+" productos..");// Añadimos un producto.
                    }else{
                       System.out.println("La tienda no existe.");//Mensaje de que la tienda no existe.
                    }
                    break;
                case 7:
                    addCliente(data);
                    break;
                case 8:
                    deleteCliente(data);
                    break;
                case 9://Salvar copia de seguridad;
                    File backup=new File("datos.backup");
                    data.saveData(backup);//Utilizamos el savedata para crear la copia de seguridad con el estado actual de los datos. 
                    break;
                case 10://ver noticias
                   NoticiasParser elPais=new NoticiasParser(elPaisDir);
                   ArrayList <Noticia> noticias=elPais.dh.noticias;
                   for(Noticia n:noticias){
                       System.out.println(n.toString());
                   }
                    break;
                //ver contenido:
                case 11:
                    mostrar(data);
                    break;
                case 0:
                    salir=true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            data.saveData(Data.archivo);
         System.out.print(data.toString());
        }
    }
    
    //GESTION TIENDAS--------------------------------------------------------------------------------
    private static Tienda searchTienda(Data data){
        ArrayList<Tienda> tiendas=data.tiendasLista;
        String nombre=readString("Introduzca nombre de la tienda");
        String ciudad=readString("Introduzca ciudad de la tienda");
        for(int i=tiendas.size()-1;i>=0;i--){// Empiezo por el final porque al borrar un indice, los siguientes reducen en 1 el suyo (problema de 2 tiendas iguales consecutivas, el salto hace que se pase por alto).
            Tienda t=tiendas.get(i);//Tienda que hay en el indice i.
            //Si coinciden los datos, borramos la tienda.
            if(t.getNombre().equals(nombre) && t.getCiudad().equals(ciudad)){
                return data.tiendasLista.get(i);//Si hay coincidencia, devuelve la tienda encontrada.
            }
        }
        return null;//Si acaba el bucle sin coincidencias, devuelve null.
    }
    private static void addTienda(Data data) {
        String nombre=readString("Introduzca nombre de la nueva tienda");
        String ciudad=readString("\"Introduzca ciudad donde se va a encontrar la tienda\n");
        Tienda t=new Tienda(nombre, ciudad);
        data.tiendasLista.add(t);
    }
    private static int borraTienda(Data data) {
        int contador=0;
        Tienda t=searchTienda(data);
        if(t!=null){
            data.tiendasLista.remove(t);
            contador++;
        }
        return contador;
    }
    //GESTION PRODUCTOS--------------------------------------------------------
    private static Producto searchProducto(Tienda tienda, int id){
        ArrayList<Producto> productos=tienda.getProductos();
        for(int i=productos.size()-1;i>=0;i--){// Empiezo por el final porque al borrar un indice, los siguientes reducen en 1 el suyo (problema de 2 tiendas iguales consecutivas, el salto hace que se pase por alto).
            Producto p=productos.get(i);//Tienda que hay en el indice i.
            //Si coinciden los datos, borramos la tienda.
            if(p.getIdentificador()==id){
                return p;//Si hay coincidencia, devuelve la tienda encontrada.
            }
        }
        return null;//Si acaba el bucle sin coincidencias, devuelve null.
    }
    private static void addProducto(Tienda tienda) {
        //Solicitar los datos del producto a añadir.-----------------------------------------------------------------------------------------
        int id=Integer.parseInt(readString("Introduzca identificador del nuevo producto:"));
        String descripcion=readString("Introduzca una breve descripcion");
        double precio=Double.parseDouble(readString("Introduzca precio del producto:"));
        int cantidad=Integer.parseInt(readString("Introduzca numero de unidades:"));
       
        //Comprobar si el producto ya existe en la tienda. Se cargará el producto o quedará como null, segun exista o no.---------------------
        Producto p=searchProducto(tienda,id);
        if(p==null){
            p=new Producto(id,descripcion, precio,cantidad);
            tienda.getProductos().add(p);
        }else{//El producto ya existe, se actualiza.
            p.setCantidad(cantidad);
            p.setDescripcion(descripcion);
            p.setCantidad(cantidad);
        }
    }
    private static int deleteProducto(Tienda tienda) {
        int contador=0;
        int id=Integer.parseInt(readString("Introduzca id del producto:"));
        //Producto p=searchProducto(tienda,id);
        Producto p=tienda.searchProducto(id);
        if(p!=null){
            tienda.getProductos().remove(p);
            contador++;
        }
        return contador;
    }
    //GESTION DE EMPLEADOS.
    private static void addEmpleado(Tienda tienda) {
        String nombre=readString("Introduzca Nombre del empleado:");
        String apellidos=readString("Introduzca descripción del producto:");
        
        Empleado nuevoEmpleado=new Empleado(nombre,apellidos);
        //Comprobar si el producto ya existe en la tienda. Se cargará el producto o quedará como null, segun exista o no.---------------------
        Empleado e=searchEmpleado(tienda,nuevoEmpleado);
        if(e==null){
            tienda.getEmpleados().add(nuevoEmpleado);
        }else{//El empleado ya existe, se actualiza.
            System.out.println("El empleado ya existe en esa tienda.");
        }
    }
    private static Empleado searchEmpleado(Tienda tienda,Empleado empAux){
        ArrayList<Empleado> empleados=tienda.getEmpleados();
        for(int i=empleados.size()-1;i>=0;i--){// Empiezo por el final porque al borrar un indice, los siguientes reducen en 1 el suyo (problema empleado duplicado, el salto hace que se pase por alto).
            Empleado e=empleados.get(i);//Empleado que hay en el indice i.
            //Si coinciden los datos, borramos la tienda.
            if(e.equals(empAux)){
                return e;//Si hay coincidencia, devuelve el empleado.
            }
        }
        return null;//Si acaba el bucle sin coincidencias, devuelve null.
    }
    private static int deleteEmpleado(Tienda tienda){
        int contador=0;
        Empleado empAux=new Empleado(readString("Nombre del empleado a borrar"),readString("Apellido del empleado a borrar"));
        
        Empleado e=searchEmpleado(tienda,empAux);
        if(e!=null){
            tienda.getEmpleados().remove(e);
            contador++;
        }
        return contador;
        
    }
    //GESTION CLIENTES----------------------------------------------------------------------------------------------------------
    private static void addCliente(Data data) {
        String nombre;
        String apellido;
        String email;
        Cliente c=null;
        nombre=readString("Introduzca el nombre del cliente");
        apellido=readString("Introduzca los apellidos del cliente");
        email=readString("Introduzca email del cliente");
        //Comprobar si el producto ya existe en la tienda. Se cargará el producto o quedará como null, segun exista o no.---------------------
        c=searchCliente(data,nombre,apellido,email);
        if(c==null){
            c=new Cliente(nombre, apellido, email);
            data.clientesLista.add(c);
        }else{//El producto ya existe, se actualiza.
            System.out.println("El Cliente ya existe");
        }
    }
    
    private static Cliente searchCliente(Data data,String nombre, String apellidos, String email){
        ArrayList<Cliente> clientes=data.clientesLista;
        for(int i=clientes.size()-1;i>=0;i--){// Empiezo por el final porque al borrar un indice, los siguientes reducen en 1 el suyo (problema empleado duplicado, el salto hace que se pase por alto).
            Cliente c=clientes.get(i);//Empleado que hay en el indice i.
            //Si coinciden los datos, borramos la tienda.
            if(c.getNombre().equals(nombre)&& c.getApellidos().equals(apellidos)&&c.getEmail().equals(email)){
                return c;//Si hay coincidencia, devuelve la tienda encontrada.
            }
        }
        return null;//Si acaba el bucle sin coincidencias, devuelve null.
    }
    private static int deleteCliente(Data data ){
        int contador=0;
        String nomAux=readString("Nombre del Cliente a borrar");
        String apelAux=readString("Apellido del Cliente a borrar");
        String emailAux=readString("Email del Cliente a borrar");
        Cliente c=searchCliente(data, nomAux,apelAux,emailAux);
        if(c!=null){
            data.getClientesLista().remove(c);
            contador++;
        }
        return contador;
    }
        
    private static String readString(String mensaje){
        Scanner sc=new Scanner(System.in);
        System.out.println(mensaje);
        String resultado=sc.nextLine();
        return resultado;
    }

    private static void mostrar(Data data) {
        ArrayList<Tienda> tiendas=data.getTiendasLista();
        ArrayList<Cliente> clientes=data.getClientesLista();
        
        for(Tienda t:tiendas){
            System.out.println(t.getNombre()+" en "+ t.getCiudad());
            ArrayList<Empleado> empleados=t.getEmpleados();
            ArrayList<Producto> prods=t.getProductos();
             System.out.println("productos");
            for(Producto p:prods){
                 System.out.println("----"+p.toString());
            }
             System.out.println("empleados");
            for(Empleado e:empleados){
                 System.out.println("----------"+e.toString());
            }
        }
        System.out.println("CLIENTES----------------");
        for(Cliente c:clientes){
            System.out.println(c.getNombre()+" ap:"+c.getApellidos()+" mail:"+c.getEmail());
        }
    }
    
}
