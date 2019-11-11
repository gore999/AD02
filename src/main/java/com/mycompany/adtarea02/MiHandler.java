/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Carlos
 */
class MiHandler extends DefaultHandler{
    //Handler(manejador) de los datos xml, pensado para documentos rss del el Pais.    
    ArrayList <Noticia> noticias=new ArrayList();
    Noticia noticiaAux=null;
    StringBuffer buffer=new StringBuffer();
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Gestionamos lo que se hace al final de un elemento, el switch determina que se hace en cada caso.
        switch(qName){
            case "item"://Cuando acaba un elemento noticia no hay que hacer nada, esta parte del codigo sobraría.
                break;
            case "title"://Cuando finaliza un elemento title (el que lleva el titular), guardamos el texto del buffer en 
                if(noticiaAux!=null){
                    String titulo=buffer.toString();//convertimos en cadena.
                    noticiaAux.setTexto(titulo);//se lo añadimos al objeto noticia.
                }
                break;
            
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch(qName){
            case "item"://Creamos un nuevo objeto noticia
                this.noticiaAux=new Noticia();
                noticias.add(noticiaAux);//lo añadimos al arraylist de noticias.
                break;
            case "title"://Al encontrar una marca title, vaciamos el buffer, lo que aparezca a partir de ahora, nos interesa para almacenar en el objeto noticia.
                buffer.delete(0, buffer.length());//vaciamos el buffer.
                break;
                
        }
    }
    
}
