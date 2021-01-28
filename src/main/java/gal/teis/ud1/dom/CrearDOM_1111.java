/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.dom;

import static gal.teis.ud1.dom.CrearDOM_11111.tratarNodoRecursivo;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;

/**
 *
 * Se crea un fichero con objetos de tipo Persona. Se consultan datos del
 * fichero de objetos como el tipo de objeto y sus propiedades. Se crear un
 * árbol DOM a partir del ficehro de objetos de tipo Persona Se muestra el árbol
 * DOM por pantalla con métodos específicos.
 *
 * @author Esther Ferreiro
 *
 * @version 1.2
 */
public class CrearDOM_1111 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        File elFicheroObjetos = null; //para almacenar la instancia del fichero serializado de objetos

        System.out.println("\n");
        System.out.println("************************************");
        System.out.println("SE CREA UN FICHERO DE OJETOS PERSONA");

        //Se crea un fichero de objetos de tipo Persona
        elFicheroObjetos = Lib_FicheroSerializablePersonas.crearFicheroSerializable("ficheropersonas_1111.dat");

        System.out.println("*********************************************************************************************************");
        System.out.println("SE MUESTRA LOS DATOS DE LOS OBJETOS DEL FICHERO, PARA CONOCER LAS CARACTERISTICAS DEL OBJETO QUE ALMACENA");

//Para consultar el tipo de objeto almacenado de un fichero de Ojetos y sus propiedades
        Lib_FicheroSerializablePersonas.mostrarDatosObjetoFicheroSerializable(elFicheroObjetos);

        /**
         * *******************************************************************************************
         */
        /* Crear árbol DOM a partir de un fichero de objetos y mostrar por pantalla el XML que se genera*/
        try {
            /*La clase DocumentBuilderFactory se utiliza para obtener una instancia de la clase DocumentBuilder*/
            DocumentBuilder builderDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            /*Crear una instancia de DOMImplementation que permite crear documentos DOM
            Creamos un documento vacío (document) con el nodo raíz de nombre "personas"*/
            Document documento = builderDocument.getDOMImplementation().createDocument(null, "personas", null);

            documento.setXmlVersion("1.0");//Asignar la versión de XML 

            /*La instancia de Document se puede un árbol DOM en pantalla.
            A partir de la instancia Document podemos
            1. Crear un documento DOM
            2. Crear un fichero XML en el disco.
            3. Mostrar por pantalla el contenido del árbol DOM en formato XML
            4. Recorrer el árbol DOM elemento a elemento para su procesamiento (mostrar en pantalla, realizar búsquedas, modificar el árbol, etc.*/
 /*  1. Crear un documento DOM
            Recorremos el fichero con los datos de las personas y por cada registro
            crearemos un nodo node_persona con 2 nodos hijos: nome y idade. Cada nodo hijo
            tendrá un valor*/
            crearDOMofFicheroSerializado(documento);

            /*2. Crear un fichero XML en el disco.
            Crea un fichero XML a partir del documento DOM*/
            crearFicheroXML(documento, "personas_1111.xml");

            /*3. Mostrar por pantalla el contenido del árbol DOM en formato XML
            Se muestra el documento DOM  en pantalla en formato XML*/
            mostrarDOM_Stream(documento);

            /*4. Recorrer el árbol DOM elemento a elemento para su procesamiento 
                (mostrar en pantalla, realizar búsquedas, modificar el árbol, etc.
            Leer, recorriendo su estructura el árbol DOM y mostrarlo por pantalla*/
            mostrarDOM_RecorriendoNodos(documento);

        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(CrearDOM_1111.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /**
     * Crea un fichero de texto XML a partir de la instancia Document que se
     * pasa por parámetro
     *
     * @param documento tipo Document, fichero XML en memoria que se modifica
     * @param nombreFichero tipo String, nombre del fichero que se creará
     * @throws TransformerException
     */
    static void crearFicheroXML(Document documento, String nombreFichero) throws TransformerException {
        /*Se determina el elemento Document (árbol DOM) que tiene la información
        que queremos pasar al fichero de texto xml*/
        Source sourceDOM = new DOMSource(documento);
        /*Se crea un Stream que tiene como destino el fichero de texto XML que se quiere crear
        a partir del árbol DOM*/
        Result resultadoFichero = new StreamResult(new File(nombreFichero));
        /*Obtenemos una instancia de la clase Transformer que permitirá pasar el árbol DOM
        a un fichero XML*/
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
    static void mostrarDOM_Stream(Document documento) throws TransformerException {
        //Se crea el fichero XML a partir del documento
        Source sourceDOM = new DOMSource(documento);
        //Se obtiene un TransformerFactory
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //Mostramos el fichero en pantalla
        Result resultadoConsola = new StreamResult(System.out);
        System.out.println("\n\n");
        System.out.println("*****************************************************************************");
        System.out.println("Contenido del árbol DOM creado automáticamente para ser mostrado por pantalla");
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
     * @param documento tipo Document, tipo Document, árbol DOM que se modifica
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
     * Parsea un documento XML contenido en un fichero *.xml y crea un árbol DOM
     * en memoria. Recorre la estructura de un árbol DOM en memoria y muestra su
     * estructura y contenido por pantalla
     *
     * @param documento tipo Document, árbol DOM
     */
    static void mostrarDOM_RecorriendoNodos(Document documento) {

        System.out.println("\n\n");
        System.out.println("*****************************");

        System.out.println("LECTURA DIRECTA DEL DOCUMENTO DOM. "
                + "El documento DOM se ha creado directamente a partir"
                + "de un fichero con el método parse() de DocumentBuilder");

        //Se muestra el elemento raíz del DOM
        System.out.println("Elemento raíz del documento XML: "
                + documento.getDocumentElement().getNodeName());

        //documento.getDocumentElement().getno
        System.out.println();

        //Obtenemos unha lista de nodos con nombre "persona" de todo el documento
        NodeList listaPersonas = documento.getElementsByTagName("persona");

        //Mostramos el nº de nodos llamados "persona" que existen en el DOM (documento)
        System.out.println("El nº de elementos de tipo persona en el DOM es " + listaPersonas.getLength());

        /**
         * ******Recorrer la lista conociendo los
         * tags*****************************
         */
        /*Una vez tenemos almacenados los datos del nodo “persona” en listaPersonas
            podemos leer su contenido teniendo en cuenta que este código depende de que 
            conozcamos la estructura y etiquetas utilizadas.*/
        for (int i = 0; i < listaPersonas.getLength(); i++) { //Recorrer la lista de elementos listaPersonas 

            //Extraemos el elemento i de la lista de nodos creada
            Node node_persona = listaPersonas.item(i);

            if (node_persona.getNodeType() == Node.ELEMENT_NODE) {//tipo de nodo
                //Obtener los datos del nodo "node_persona".
                Element elemento = (Element) node_persona;//obter os elementos do nodo
                System.out.println("El contenido del atributo id del elemento es " + elemento.getAttribute("id"));
                System.out.println("Nombre : " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Edad: " + elemento.getElementsByTagName("edad").item(0).getTextContent());
                System.out.println();
            }
        }

        /**
         * ******Recorrer la lista SIN CONOCER los tags, sabiendo los niveles
         * existentes*********************
         */
        /*En el caso de no conocer la estructura del documento DOM, se 
            recorrería el árbol de la siguiente forma*/
        for (int i = 0; i < listaPersonas.getLength(); i++) {
            //Extraer un nodo de la lista para consultar sus hijos
            Node elNodo = listaPersonas.item(i);

            //Si el elemento que se ha extraido es tip NODE
            if (elNodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elElement = (Element) elNodo;

                if (elElement.hasAttributes()) {
                    NamedNodeMap miListaAtributos = elNodo.getAttributes();
                    for (int j = 0; j < miListaAtributos.getLength(); j++) {
                        System.out.println("El atributo es " + miListaAtributos.item(j).getNodeName()
                                + " y su contenido: " + miListaAtributos.item(j).getNodeValue());
                    }
                }
                //Analizamos si tiene hijos y mostramos su contenido
                if (elElement.hasChildNodes()) {
                    NodeList elNodeList = elNodo.getChildNodes();
                    for (int j = 0; j < elNodeList.getLength(); j++) {
                        Node elNode = elNodeList.item(j);
                        //Mostrar el tag del nodo y su valor
                        System.out.println(elNode.getNodeName() + ": " + elNode.getTextContent());
                        /* Hay otra forma de acceder al texto de una etiqueta
                             teniendo en cuenta de que el texto es también un hijo del elemento
                            System.out.println(elNode.getFirstChild().getNodeValue());*/
                    }
                }
            }
        }
        /**
         * ******Recorrer la lista SIN CONOCER los tags NI LOS NIVELES
         * existentes*********************
         */
        System.out.println("Acceso recursivo");
        tratarNodoRecursivo(documento);
    }

    /**
     * Recorre el árbol DOM de forma recursiva, a partir del elemento raíz
     *
     * @param nodo Tipo Node, nodo del DOM a tratar
     * @param ind Tipo String, determina la indentación de la salida por
     * pantalla
     */
    public static void tratarNodoRecursivo(Node nodo) {
        switch (nodo.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("<xml version=\"1.0\">");
                Document doc = (Document) nodo;
                tratarNodoRecursivo(doc.getDocumentElement());
                break;

            case Node.ELEMENT_NODE:
                Element elemento = (Element) nodo;
                String nombre = elemento.getNodeName();
                System.out.print("\t<" + nombre);
                NamedNodeMap atributos = elemento.getAttributes();
                if (atributos.getLength() != 0) {
                    for (int i = 0; i < atributos.getLength(); i++) {
                        tratarNodoRecursivo(atributos.item(i));
                    }
                }
                System.out.println(">");

                NodeList hijos = nodo.getChildNodes();
                if (hijos != null) {
                    for (int i = 0; i < hijos.getLength(); i++) {
                        tratarNodoRecursivo(hijos.item(i));
                    }
                }
                System.out.println("\t" + "</" + nombre + ">");
                break;

            case Node.ATTRIBUTE_NODE:
                Attr atributo = (Attr) nodo;
                System.out.print(" " + atributo.getNodeName()
                        + " = " + atributo.getNodeValue());
                break;
                
            case Node.TEXT_NODE:
                String texto = nodo.getTextContent();
                System.out.println("\t\t" + texto);
        }
    }

    /**
     * Crea un un documento DOM a partir de un fichero serializado de objetos de
     * la clase Persona
     *
     * @param documento tipo Document, representa el documento DOM que se va a
     * crear a partir de un fichero de objetos
     */
    static void crearDOMofFicheroSerializado(Document documento) {
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
                //Creamos el nodo node_persona y lo enlazamos al raíz del documento
                Element elemento = crearElemento("persona", documento);
                //Agregamos nome e idade
                crearSubElemento(prop1, amigo.getNome(), elemento, documento);
                crearSubElemento(prop2, String.valueOf(amigo.getIdade()), elemento, documento);
                //Añadiremos un atributo a cada elemento node_persona llamado id que contendrá su 
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
