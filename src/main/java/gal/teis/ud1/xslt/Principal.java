/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.xslt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            transformar();
        } catch (TransformerException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void transformar() throws FileNotFoundException, TransformerConfigurationException, TransformerException, IOException{
        String hojaEstilo = "alumnadoPlantilla.xsl";
        String ficheroXML = "alumnado.xml";
        File paginaHTML = new File ("miSuperWeb.html");
        
        //Crear un flujo hacia el File de la página Web que quiero crear
        FileOutputStream elFileOutputStream = new FileOutputStream (paginaHTML);
        
        //Crear una fuente de datos a partir de la hoja de estilos
        Source estilos = new StreamSource (hojaEstilo);
        //Crear una fuente de datos a partir del fichero XML
        Source datos = new StreamSource (ficheroXML);
        
        /*Ya tenemos el stream de los ficheros origen (el fichero XML y el XSL)
        y del fichero destino (la página Web)*/
        //Creamos el resultado a partir de flujo hacia el fichero HTML
        Result resultado = new StreamResult (elFileOutputStream);
        
        //Ejecutamos la transformación
        Transformer elTransformer = TransformerFactory.newInstance().
                newTransformer(estilos);
        elTransformer.transform(datos, resultado);
        elFileOutputStream.close();
    }
    
}
