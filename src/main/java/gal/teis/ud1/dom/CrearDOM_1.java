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
 * @version 1.0 Primera aproximación al procesamiento de información XML con DOM
 * Este clase crea un fichero XML utilizando el parser DOM A partir del
 * documento DOM se creará un fichero XML y se verá como mostrar la información
 * del DOM por consola.
 */
public class CrearDOM_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Valores de los elementos a introducir en el XML*/
        Integer id;
        String apellidos;
        String nombre;
        Integer dep;
        Double salario;

        try {
            //Crear una nueva instancia de una fábrica de constructores de documentos
            DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
            //Crear un parser/procesador de documentos XML
            DocumentBuilder builderDocument = factoryDocument.newDocumentBuilder();
            //Crear una instancia de DOMImplementation que permite crear documentos DOM
            DOMImplementation implementacionDOM = builderDocument.getDOMImplementation();
            
            /**
             * ****************Crear el documento DOM con su elemento raíz**************************
             */
            //Crear un documento vacío (document) con el nodo raíz de nombre persoas
            Document documento = implementacionDOM.createDocument(null, "personas_1", null);
            //Asignar la versión de XML 
            documento.setXmlVersion("1.0");

            /**
             * ****************Añadir primer elemento**************************
             */
            //Crear un elemento hijo del raíz llamado persona
            Element elemento1 = documento.createElement("persona");
            //Enlazar el elemento raíz al documento creado
            documento.getDocumentElement().appendChild(elemento1);

            //Agregamos id, nombre, edad, apellidos, dep y saladio y lo enlazamos con elemento1
            //Definimos variables de un tipo concreto para estos elementos
            //Estas varibles son de tipos concretos que podrían haber sido tomadas de otras fuentes
            //como una base de datos.
            id = 1;
            apellidos = "Pin";
            nombre = "Ana";
            dep = 45;
            salario = 2500.0;
            //El DOM necesita que todos sus elementos sean convertidos a String
            crearElementoHijoConTexto("nombre", nombre, elemento1, documento);
            crearElementoHijoConTexto("apellidos", apellidos, elemento1, documento);
            crearElementoHijoConTexto("id", id.toString(), elemento1, documento);
            crearElementoHijoConTexto("dep", dep.toString(), elemento1, documento);
            crearElementoHijoConTexto("salario", salario.toString(), elemento1, documento);

            /**
             * ****************Añadir segundo elemento**************************
             */
            //Crear otro elemento hijo del raíz llamado persona
            Element elemento2 = documento.createElement("persoa");
            //Enlazar el elemento raíz al documento creado
            documento.getDocumentElement().appendChild(elemento2);

            id = 2;
            apellidos = "Rois";
            nombre = "Pedro";
            dep = 35;
            salario = 2200.0;
            //El DOM necesita que todos sus elementos sean convertidos a String
            crearElementoHijoConTexto("nombre", nombre, elemento1, documento);
            crearElementoHijoConTexto("apellidos", apellidos, elemento1, documento);
            crearElementoHijoConTexto("id", id.toString(), elemento1, documento);
            crearElementoHijoConTexto("dep", dep.toString(), elemento1, documento);
            crearElementoHijoConTexto("salario", salario.toString(), elemento1, documento);

            /**
             * ****************Crear un fichero XML con el contenido del DOM**************************
             */
            /*Se determina el elemento Document (árbol DOM) que tiene la información
             que queremos pasar al fichero de texto xml*/
            Source sourceDOM = new DOMSource(documento);
            /*Se crea un Stream que tiene como destino el fichero de texto XML que se quiere crear
            a partir del árbol DOM*/
            Result resultado = new StreamResult(new File("personas.xml"));
            /*Obtenemos una instancia de la clase Transformer que permitirá pasar el árbol DOM
            a un fichero XML*/
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            //Se realiza la transformación del documento a fichero
            transformer.transform(sourceDOM, resultado);

            /**
             * ****************Mostrar el contenido del DOM por pantalla**************************
             */
            //Mostramos por consola la fuente sourceDOM
            Result consola = new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Se ha producido una excepción " + e.toString());
        }
    }

    /**
     * Crea un nuevo elemento con valor y lo enlaza a su elemento padre en el
     * documento DOM
     *
     * @param hijo tipo String, nuevo elemento del DOM
     * @param valor tipo String, valor del nuevo elemento hijo
     * @param padre tipo Element, elemento padre ya existente
     * @param documento tipo Document, DOM que se va a modificar
     */
    static void crearElementoHijoConTexto(String hijo, String valor,
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
