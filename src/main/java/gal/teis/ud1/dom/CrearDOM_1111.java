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
import org.xml.sax.SAXException;

/**
 *
 * @author Esther Ferreiro Se crea un fichero con objetos de tipo Persona y, a
 * continuación, se crea un documento DOM y un fichero XML a partir de los datos
 * almacenados en el fichero Se consultan los datos de la clase y propiedades de
 * objetos almacenados en el fichero serializado Se añaden atributos al árbol
 * DOM. Después ser recorre el árbol y se muestra por pantalla
 *
 * @version 1.2
 */
public class CrearDOM_1111 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Se crea un fichero de objetos de tipo Persona
        Lib_FicheroSerializablePersonas.crearFicheroSerializable("ficheropersonas_1111.dat");

        //Para consultar el tipo de objeto almacenado de un fichero de Ojetos y sus propiedades
        Lib_FicheroSerializablePersonas.mostrarDatosObjetoFicheroSerializable("ficheropersonas_1111.dat");

        /**
         * *******************************************************************************************
         */
        /* Crear árbol DOM a partir de un fichero de objetos y mostrar por pantalla el XML que se genera*/
        try {
            //Crear una nueva instancia de una fábrica de constructores de documentos
            DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
            //Crear un parser/procesador de documentos XML
            DocumentBuilder builderDocument = factoryDocument.newDocumentBuilder();
            //Crear una instancia de DOMImplementation que permite crear documentos DOM
            DOMImplementation implementacionDOM = builderDocument.getDOMImplementation();
            //Creamos un documento vacío (document) con el nodo raíz de nombre "personas"
            Document documento = implementacionDOM.createDocument(null, "personas", null);
            //Asignar la versión de XML 
            documento.setXmlVersion("1.0");

            /*Recorremos el fichero con los datos de las personas y por cada registro
            crearemos un nodo persona con 2 nodos hijos: nome y idade. Cada nodo hijo
            tendrá un valor*/
            crearElementoOfFicheroSerializable(documento);

            //Crea un fichero XML a partir del documento XML
            crearFicheroXML(documento, "personas_1111.xml");

            //Se muestra el documento XML creado en pantalla
            mostrarFicheroXML(documento);

            //Mostrar el árbol DOM por pantalla
            leerFicheroXMLPersona();

        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(CrearDOM_1111.class.getName()).log(Level.SEVERE, null, e);
        }

        /* *******************************************************************************************
        /* Generar un árbol DOM a partir de un fichero XML y recorrerlo*/
        try {
            //Crear un parser/procesador de documento XML a partir de una fábrica de constructores de documentos
            DocumentBuilder factoryDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Procesamos el fichero XML y obtenemos nuestro objeto Document  
//Document    Document documento = documentBuilder.parse(new InputSource(new FileInputStream("/ruta_a_fichero/fichero.xml")));  
        } catch (Exception e) {
            Logger.getLogger(CrearDOM_1111.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Crea un fichero XML a partir de la instancia Document que se pasa por
     * parámetro
     *
     * @param documento tipo Document, fichero XML en memoria que se modifica
     * @param nombreFichero tipo String, nombre del fichero que se creará
     * @throws TransformerException
     */
    static void crearFicheroXML(Document documento, String nombreFichero) throws TransformerException {
        //Se crea el fichero XML a partir del documento
        Source sourceDOM = new DOMSource(documento);
        //Se crea el resultado en el fichero Personas.xml
        Result resultadoFichero = new StreamResult(new File(nombreFichero));
        //Se obtiene un TransformerFactory
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //Se realiza la transformación del documento a fichero
        transformer.transform(sourceDOM, resultadoFichero);
    }

    /**
     * Muestra el contenido XML a partir de la instancia Document que se pasa
     * por parámetro
     *
     * @param documento tipo Document, fichero XML en memoria que se modifica
     * @throws TransformerException
     */
    static void mostrarFicheroXML(Document documento) throws TransformerException {
        //Se crea el fichero XML a partir del documento
        Source sourceDOM = new DOMSource(documento);
        //Se obtiene un TransformerFactory
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //Mostramos el fichero en pantalla
        Result resultadoConsola = new StreamResult(System.out);
        transformer.transform(sourceDOM, resultadoConsola);
    }

    /**
     * Crea un nuevo elemento que enlazará al raíz del documento
     *
     * @param nombreElemento tipo String, nombre del nuevo elemento
     * @param documento tipo Document, fichero XML en memoria que se modifica
     * @return tipo Element enlazada al raíz del documento XML
     */
    static Element crearElemento(String nombreElemento, Document documento) {
        Element elemento = documento.createElement(nombreElemento);
        //Enlazamos el elemento raíz al documento creado
        documento.getDocumentElement().appendChild(elemento);
        return elemento;
    }

    /**
     * Crea un atributo con su valor y lo agrega al elemento que se pasa por
     * parámetro
     *
     * @param elemento Tipo Element Elemento al que se le va añadir un
     * atributo/valor
     * @param atributo Tipo String Nombre del atributo
     * @param valor Tipo String Valor del atributo
     * @param documento Tipo Dcument Árbol DOM que se modifica
     */
    static void agregarAtributoElemento(Element elemento, String atributo, String valor, Document documento) {
        //Defino un elemento de tipo Attr (atributo) con el nombre que se pasa por parámetro
        Attr elAttr = documento.createAttribute(atributo);
        //Dar valor al atributo recien creado atributo=valor
        elAttr.setValue(valor);
        //Enlazo el atributo con su correspondiente elemento
        elemento.setAttributeNode(elAttr);
    }

    /**
     * Crea un nuevo elemento con valor de tipo texto que se enlaza a un
     * elemento padre
     *
     * @param hijo tipo String, nombre del nuevo elemento hijo
     * @param valor tipo String, valor del elemento hijo
     * @param padre tipo Element, elemento padre del nuevo elemento hjo
     * @param documento tipo Document, fichero XML en memoria que se modifica
     */
    static void crearSubElemento(String hijo, String valor,
            Element padre, Document documento) {
        //Creamos el elemento
        Element elemento = documento.createElement(hijo);
        //Le damos valor al elemento creado
        Text texto = documento.createTextNode(valor);
        //Elazamos el hijo con el padre
        padre.appendChild(elemento);
        //Le damos valor al elemento creado
        elemento.appendChild(texto);
    }

    /**
     * Recorre el árbol DOM y lo muestra por pantalla
     */
    static void leerFicheroXMLPersona() {
        try {
            /*Crear el parser DOM
             */
            //Crear una nueva instancia de una fábrica de constructores de documentos
            DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
            //Crear un parser/procesador de documentos XML
            DocumentBuilder builderDocument = factoryDocument.newDocumentBuilder();

            //Invocamos el documento XML que se quiere leer
            Document documento = builderDocument.parse(new File("personas_1111.xml"));
            documento.getDocumentElement().normalize();
            System.out.println("");
            System.out.println("Elemento raíz do documento XML: "
                    + documento.getDocumentElement().getNodeName());
            //Crea unha lista con todos los nodos 'persona'
            NodeList listaPersonas = documento.getElementsByTagName("persona");
            //Percorremos a lista que acabamos de crear cun bucle for
            for (int i = 0; i < listaPersonas.getLength(); i++) {
                Node persona = listaPersonas.item(i);
                if (persona.getNodeType() == Node.ELEMENT_NODE) {//tipo de nodo
                    //obter datos do nodo empregado (fillos do pai).
                    Element elemento = (Element) persona;//obter os elementos do nodo
                    System.out.println("Nombre : " + getNodo("nome", elemento));
                    System.out.println("Apelido: " + getNodo("idade", elemento));
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            Logger.getLogger(CrearDOM_1111.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Obtén valor dun nodo se lle pasamos certa información
    private static String getNodo(String etiqueta, Element elem) {
        NodeList nodo = elem.getElementsByTagName(etiqueta).item(0).getChildNodes();
        Node valorNodo = (Node) nodo.item(0);
        //Devolve valor do nodo
        return valorNodo.getNodeValue();
    }

    /**
     * Crea un un documento DOM a partir de un fichero serializado de objetos de
     * la clase Persona
     *
     * @param documento tipo Document, representa el documento DOM que se va a
     * crear a partir de un fichero de objetos
     */
    static void crearElementoOfFicheroSerializable(Document documento) {
        //nombres de las propiedades del objeto Persona
        String prop1 = "nombre";
        String prop2 = "edad";

        //Definimos un objeto de tipo Persona
        Persona amigo;
        int i = 0; //posición del objeto en el fichero, será un atributo en XML
        //Declaramos o ficheiro.Se non existe, créao.
        File fichero = new File("ficheropersonas_111.dat");
        //Creamos un fluxo de entrada: ficheiro -> aplicación
        try (FileInputStream miFIS = new FileInputStream(fichero);
                ObjectInputStream miOIS = new ObjectInputStream(miFIS);) {
            //Bucle para ler do ficheiro
            //Cando chega ao fin de arquivo amigo é null!!!
            while (true) {
                i++;
                amigo = (Persona) miOIS.readObject();
                //Visualizamos contido do ficheiro
                System.out.println("Nombre: " + amigo.getNome()
                        + "   Edad: " + amigo.getIdade());
                //Creamos el nodo persona y lo enlazamos al raíz del documento
                Element elemento = crearElemento("persona", documento);
                //Agregamos nome e idade
                crearSubElemento(prop1, amigo.getNome(), elemento, documento);
                crearSubElemento(prop2, String.valueOf(amigo.getIdade()), elemento, documento);
                //Añadiremos un atributo a cada elemento persona llamado id que contendrá su 
                //posición en el fichero 
                agregarAtributoElemento(elemento, "id", String.valueOf(i), documento);
            }
        } catch (EOFException e) {
            System.out.println("Se ha alacanzado el fin del fichero serializable");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Se ha producido una excepción " + e.toString());
        }
    }

}