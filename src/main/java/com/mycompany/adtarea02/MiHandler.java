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
    ArrayList <Noticia> noticias=new ArrayList();
    Noticia noticiaAux=null;
    StringBuffer buffer=new StringBuffer();
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch(qName){
            case "item"://Creamos un nuevo objeto noticia
                break;
            case "title":
                if(noticiaAux!=null){
                    String titulo=buffer.toString();
                    noticiaAux.setTexto(titulo);
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
            case "title":
                buffer.delete(0, buffer.length());//vaciamos el buffer.
                break;
                
        }
    }
    
}
