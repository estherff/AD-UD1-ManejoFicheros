/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.dom;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 * Primera aproximación al procesamiento de información XML con DOM
 * Este clase crea un fichero XML utilizando el parser DOM
 * A partir del documento DOM se creará un fichero XML y se verá como mostrar
 * la información del DOM por consola.
 */
public class CrearDOM_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //Crear una nueva instancia de una fábrica de constructores de documentos
            DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
            //Crear un parser/procesador de documentos XML
            DocumentBuilder builderDocument = factoryDocument.newDocumentBuilder();
            //Crear una instancia de DOMImplementation que permite crear documentos DOM
            DOMImplementation implementacionDOM = builderDocument.getDOMImplementation();
            //Crear un documento vacío (document) con el nodo raíz de nombre persoas
            Document documento = implementacionDOM.createDocument(null, "personas", null);
            //Asignar la versión de XML 
            documento.setXmlVersion("1.0");

            //Crear un elemento hijo del raíz llamado persona
            Element elemento1 = documento.createElement("persona");
            //Enlazar el elemento raíz al documento creado
            documento.getDocumentElement().appendChild(elemento1);

            //Agregamos nombre y edad y lo enlazamos con elemento1
            CrearElementoHijoConTexto("nombre", "Ana", elemento1, documento);
            CrearElementoHijoConTexto("edad", "22", elemento1, documento);

            //Crear otro elemento hijo del raíz llamado persona
            Element elemento2 = documento.createElement("persoa");
            //Enlazar el elemento raíz al documento creado
            documento.getDocumentElement().appendChild(elemento2);

            //Agregamos nombre y edad y lo enlazamoa a elemento2
            CrearElementoHijoConTexto("nombre", "Pedro", elemento2, documento);
            CrearElementoHijoConTexto("edad", "25", elemento2, documento);

            //Crear una fuente a partir del documento DOM
            Source sourceDOM = new DOMSource(documento);

            //Crear el fichero XML "personas.xml" a partir sourceDOM
            Result resultado = new StreamResult(new File("personas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(sourceDOM, resultado);

            //Mostramos por consola la fuente sourceDOM
            Result consola = new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Se ha producido una excepción " + e.toString());
        }
    }

    /**
     * Crea un nuevo elemento con valor y lo enlaza a su elemento padre 
     * en el documento DOM
     * @param hijo tipo String, nuevo elemento del DOM
     * @param valor tipo String, valor del nuevo elemento hijo
     * @param padre tipo Element, elemento padre ya existente
     * @param documento  tipo Document, DOM que se va a modificar
     */
    static void CrearElementoHijoConTexto(String hijo, String valor,
            Element padre, Document documento) {
        //Crear un nuevo elemento a partir del parámetro hijo
        Element elemento = documento.createElement(hijo);
        //Dar valor al elemento creado a partir del parámetro valor
        Text texto = documento.createTextNode(valor);
        //Enlazar el hijo con el padre que se pasó por parámetro
        padre.appendChild(elemento);
        //Dar valor al elemento creado: el valor se trata como un elemento
        elemento.appendChild(texto);
    }

}
