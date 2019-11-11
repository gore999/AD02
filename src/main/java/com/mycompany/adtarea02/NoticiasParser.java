/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adtarea02;

import com.sun.jndi.toolkit.url.Uri;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Carlos
 */
public class NoticiasParser {
    //Clase encargada de parsear un documento xml. El resultado se almacena en el Handler.
    SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
    SAXParser saxParser;
    MiHandler dh;
    public NoticiasParser(String uri) {
        dh=new MiHandler();
        try {
            this.saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(uri, dh);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(NoticiasParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(NoticiasParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(NoticiasParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
