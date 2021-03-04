/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS for A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gal.teis.ud1.dom;

import gal.teis.ControlData;
import gal.teis.libreriadam.Menu;
import gal.teis.excepciones.NumeroFueraRangoException;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
public class Principal_DOM {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Document documento = null; //para manejar el DOM
        String nombreFichero = ""; //para alamcenar el nombre del fichero xml   
        File elFicheroObjetos = null; //para almacenar la instancia del fichero serializado de objetos

        boolean finalizar = false; //para controlar la salida del programa
        Scanner sc = new Scanner(System.in); //creo una instancia de la clase Scanner para la introdución de datos por teclado

        try {
            do {
                switch (pintarMenu()) {
                    case 1:

                        System.out.println("\n***************************************************n"
                                + "SE CREA UN FICHERO SECUENCIAL DE OJETOS DE LA CLASE PERSONA");
                        System.out.println("Introduzca el nombre del fichero  *.dat que contendrá los objetos de tipo Persona.....");
                        nombreFichero = ControlData.lerString(sc);
                        elFicheroObjetos = Lib_FicheroSerializablePersonas.crearFicheroSerializable(nombreFichero + ".dat");
                        break;

                    case 2:

                        if (!Objects.isNull(elFicheroObjetos) && elFicheroObjetos.exists()) {

                            System.out.println("\n*********************************************************************************************************+"
                                    + "\n SE MUESTRAN LOS DATOS DE LOS OBJETOS (nombre de la clase y propiedades) DEL FICHERO CREADO (OP1) A PARTIR DE LA CLASE PERSONA");
                            Lib_FicheroSerializablePersonas.mostrarDatosObjetoFicheroSerializable(elFicheroObjetos);

                        } else {

                            System.out.println("\n*********************************************************************************************************");
                            System.out.println("Debe ejecutar antes la opción 1 para poder crear su árbol DOM en memoria");
                            System.out.println("*********************************************************************************************************\n\n");
                        }
                        break;

                    case 3:

                        if (!Objects.isNull(elFicheroObjetos) && elFicheroObjetos.exists()) {

                            System.out.println("\n*********************************************************************************************************+"
                                    + "\n SE CREA UN ÁRBOL DOM DEL FICHERO CREADO (OP1) A PARTIR DE LA CLASE PERSONA");
                            //Crea el documento DOM aportándole la información del elemento raíz
                            documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                                    getDOMImplementation().createDocument(null, "personas", null);
                            documento.setXmlVersion("1.0");//Asignar la versión de XML 

                            /*Crea los elementos del documento DOM a partir de un fichero de objetos para el elemento raíz "personas"*/
                            crearElementoOfFicheroSerializable(documento, elFicheroObjetos);

                        } else {

                            System.out.println("\n*********************************************************************************************************");
                            System.out.println("Debe ejecutar antes la opción 1 para crear el fichero serializado de objetos");
                            System.out.println("*********************************************************************************************************\n\n");
                        }
                        break;

                    case 4:

                        if (!Objects.isNull(documento)) {
                            System.out.println("\n*********************************************************************************************************+"
                                    + "\n MUESTRA EL CONTENIDO DEL DOM EN PANTALLA EN FORMATO XML");
                            mostrarDOM_Stream(documento);

                        } else {
                            System.out.println("\n*********************************************************************************************************");
                            System.out.println("Debe ejecutar antes la opción 1 y 3 para poder mostrar el contenido del DOM");
                            System.out.println("*********************************************************************************************************\n\n");
                        }
                        break;

                    case 5:

                        if (!Objects.isNull(documento)) {

                            System.out.println("\n************************************\nSE CREA UN FICHERO XML A PARTIR DEL DOM EXISTENTE EN MEMORIA\n"
                                    + "Y SE MUESTRA LA INFORMACIÓN QUE CONTIENE MEDIANTE UN FLUJO DE CARACTERES");
                            System.out.println("Introduzca el nombre del fichero *.xml que contendrá el fichero XML con los datos de la clase Persona...");
                            nombreFichero = ControlData.lerString(sc);
                            crearFicheroXML(documento, nombreFichero + ".xml");

                        } else {

                            System.out.println("\n*********************************************************************************************************");
                            System.out.println("Debe ejecutar antes la opción 1 y 3 para poder crear el fichero XML a partir del DOM");
                            System.out.println("*********************************************************************************************************\n\n");

                        }
                        break;

                    case 6:
                        System.out.println("\n************************************\n"
                                + "SE RECORRE EL ÁRBOL DOM CONOCIENDO LOS TAG/ETIQUETAS DE LOS ELEMENTO\n");
                        if (!Objects.isNull(documento)) {
                            recorrerDOM_ConociendoEtiquetas(documento);
                        }
                        break;
                    case 7:
                        System.out.println("\n************************************\n"
                                + "SE RECORRE EL ÁRBOL DOM CONOCIENDO LOS NIVELES DEL ÁRBOL\n");
                        if (!Objects.isNull(documento)) {
                            recorrerDOM_ConociendoNiveles(documento);
                        }
                        break;
                    case 8:
                        System.out.println("\n************************************\n"
                                + "SE RECORRE EL ÁRBOL DOM DE FORMA RECURSIVA SIN CONOCER ETIQUETAS NI NIVELES\n");
                        if (!Objects.isNull(documento)) {
                            recorrerDOM_Recursivo(documento);
                        }
                        break;
                    case 9:
                        System.out.println("Hasta luego!!!");
                        finalizar = true;
                }
            } while (!finalizar);
        } catch (ParserConfigurationException | TransformerException e) {
            Logger.getLogger(Principal_DOM.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            sc.close();
        }

    }

    /**
     * Dibuja un menú en la consola a partir con unas opciones determinadas
     */
    static byte pintarMenu() {
        byte opcion = 0;
        boolean correcta;
        System.out.println("\n\n*******************************************************************************************************");
        /* Solo sale del While cuando se selecciona una opción correcta en rango y tipo*/
        ArrayList<String> misOpciones = new ArrayList<String>() {
            {
                add("Crear un fichero secuencial de objetos de tipo Persona");
                add("Mostrar los datos de los objetos (nombre de la clase y propiedades) del fichero Persona");
                add("Crear el arbol DOM a partir del fichero de objetos Persona");
                add("Mostrar el contenido del árbol DOM en pantalla en formato XML");
                add("Crear un fichero XML a partir del DOM creado anteriormente ");
                add("Recorrer el árbol DOM conociendo los tags/etiquetas de los elementos");
                add("Recorrer el árbol DOM conociendo sin conocer los tags/etiquetas de los elementos, pero sí los niveles");
                add("Recorrer el árbol DOM conociendo sin conocer nada de la estructura del mismo. Método recursivo");
                add("Finalizar");
            }
        };
        /*La clase Menu permite imprimir el menú a partir de los datos de un ArrayList<String>
            y utilizar métodos para control de rango*/
        Menu miMenu = new Menu(misOpciones);
        do {

            miMenu.printMenu();

            /*La clase ControlData permite hacer un control de tipo leído*/
            try {
                opcion = ControlData.lerByte(sc);
                /*miMenu.rango() lanza una excepción propia en el caso de que 
                el parámetro opcion esté fuera del rango posible */
                miMenu.rango(opcion);
                correcta = true;
            } catch (NumeroFueraRangoException e) {//Excepción personalizada
                System.out.println(e.getMessage());
                correcta = false;
            }

        } while (!correcta);

        return opcion;
    }

    /**
     * Crea un fichero de texto XML a partir de la instancia Document que se
     * pasa por parámetro y lo muestra por pantalla
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
        File elFicheroXML = new File(nombreFichero);
        Result resultadoFichero = new StreamResult(elFicheroXML);
        /*Obtenemos una instancia de la clase Transformer que permitirá pasar el árbol DOM
        a un fichero XML*/
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //Se realiza la transformación del documento a fichero
        transformer.transform(sourceDOM, resultadoFichero);
        try {
            //Accede al fichero XML como fichero de texto secuencia que es y muestra su contenido por pantalla
            System.out.println("Se muestra el contenido del fichero accediendo secuencialmente al mismo");
            leeTodosLosCaracteres(elFicheroXML);
            System.out.println("\n\n");

        } catch (IOException ex) {
            Logger.getLogger(Principal_DOM.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        System.out.println("*****************************************************************************");
        System.out.println("Contenido del árbol DOM creado automáticamente para ser mostrado por pantalla");
        transformer.transform(sourceDOM, resultadoConsola);
        System.out.println("\n\n");
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
     * Recorre la estructura de un árbol DOM en memoria y muestra su estructura
     * y contenido por pantalla Parte de que conoce la estructura XML así como
     * los nombres de las etiquetas de nodos y atributos
     *
     * @param documento tipo Document, árbol DOM
     */
    static void recorrerDOM_ConociendoEtiquetas(Document documento) {
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
         * ******Recorrer la lista conociendo los tags************************
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
                System.out.println("\nEl elemento es " + elemento.getNodeName());
                System.out.println("El contenido del atributo id del elemento es " + elemento.getAttribute("id"));
                System.out.println("Nombre : " + elemento.getElementsByTagName("nombre").item(0).getTextContent());
                System.out.println("Edad: " + elemento.getElementsByTagName("edad").item(0).getTextContent());
            }
        }

    }

    /**
     * Recorre la estructura de un árbol DOM en memoria y muestra su estructura
     * y contenido por pantalla NO conoce la estructura XML ni sus etiquetas,
     * pero sí conoce los niveles
     *
     * @param documento tipo Document, árbol DOM
     */
    static void recorrerDOM_ConociendoNiveles(Document documento) {

        System.out.println();

        //Se muestra el elemento raíz del DOM
        System.out.println("Elemento raíz del documento XML: "
                + documento.getDocumentElement().getNodeName() + "\n");

        //Obtenemos unha lista de nodos con nombre "persona" de todo el documento
        //NodeList listaPersonas = documento.getElementsByTagName(documento.getDocumentElement().getNodeName());
        //Obtenemos unha lista de nodos con nombre "persona" de todo el documento
        NodeList listaPersonas = documento.getElementsByTagName("persona");

        for (int i = 0; i < listaPersonas.getLength(); i++) {
            //Extraer un nodo de la lista para consultar sus hijos
            Node elNodo = listaPersonas.item(i);

            //Si el elemento que se ha extraido es tip NODE
            if (elNodo.getNodeType() == Node.ELEMENT_NODE) {

                Element elElement = (Element) elNodo;

                //Muestra la etiqueta del elemento 
                System.out.println("Datos del elemento "
                        + elElement.getNodeName());

                if (elElement.hasAttributes()) {
                    NamedNodeMap miListaAtributos = elNodo.getAttributes();
                    for (int j = 0; j < miListaAtributos.getLength(); j++) {
                        System.out.println("\tEl atributo es " + miListaAtributos.item(j).getNodeName()
                                + " y su contenido: " + miListaAtributos.item(j).getNodeValue());
                    }
                }
                //Analizamos si tiene hijos y mostramos su contenido
                if (elElement.hasChildNodes()) {
                    NodeList elNodeList = elNodo.getChildNodes();
                    for (int j = 0; j < elNodeList.getLength(); j++) {
                        Node elNode = elNodeList.item(j);
                        //Mostrar el tag del nodo y su valor
                        System.out.println("   " + elNode.getNodeName() + ": " + elNode.getTextContent());
                        /* Hay otra forma de acceder al texto de una etiqueta
                             teniendo en cuenta de que el texto es también un hijo del elemento
                            System.out.println(elNode.getFirstChild().getNodeValue());*/
                    }
                }
            }
        }
    }

    /**
     * Recorre la estructura de un árbol DOM en memoria y muestra su estructura
     * y contenido por pantalla No conoce nada de la estructura del DOM, ni
     * etiquetas, ni atributos, ni niveles
     *
     * @param documento tipo Document, árbol DOM
     */
    static void recorrerDOM_Recursivo(Document documento) {
        /* Se ejecuta un método recursivo que, a partir del elemento raiz, accede a todos los
        nodos y textos del árbol DOM*/
        System.out.println("Acceso recursivo");
        tratarNodoRecursivo(documento);
    }

    /**
     * Es llamado desde recorrerDOM_Recusivo para recorrer un árbol DOM y
     * mostrarlo por pantalla de forma recursiva a partir del elemento raíz
     *
     * @param nodo nodo al que accede de forma recursiva
     * @param ind nivel de indexado
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
                Text texto = (Text) nodo;
                String elTexto = texto.getTextContent();
                System.out.println("\t\t" + elTexto);
        }
    }

    /**
     * Crea un un documento DOM a partir de un fichero serializado de objetos de
     * la clase Persona
     *
     * @param documento tipo Document, representa el documento DOM que se va a
     * crear a partir de un fichero de objetos
     */
    static void crearElementoOfFicheroSerializable(Document documento, File elFicheroSerializadodeObjetos) {
        //nombres de las propiedades del objeto Persona
        String prop1 = "nombre";
        String prop2 = "edad";

        //Definimos un objeto de tipo Persona
        Persona amigo;
        int i = 0; //posición del objeto en el fichero, será un atributo en XML
        //Creamos un fluxo de entrada: ficheiro -> aplicación
        try (FileInputStream miFIS = new FileInputStream(elFicheroSerializadodeObjetos);
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
            System.out.println("Se ha alacanzado el fin del fichero serializable\n\n\n");
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Se ha producido una excepción " + e.toString());
        }
    }

    /**
     * A partir de una instacia File que representa a un fichero secuencial de
     * texto, abre el fichero y muestra la información que contiene por pantalla
     *
     * @param ficheroSecuencialTexto tipo File, representa el fichero secuencial
     * de texto a tratar
     */
    static void leeTodosLosCaracteres(File ficheroSecuencialTexto)
            throws IOException {
        //Como buffer voy a utilizar un StringBuilder 
        //que permite almacenar una cadena
        StringBuilder bufO = new StringBuilder();
        //Creo un FileReader a partir de un fichero de texto y codifico cada carazter con UTF-8
        try (
                Reader miFileReader = new FileReader(ficheroSecuencialTexto)) {
            //Creo un búfer de caracteres para los datos de entrada
            char[] bufI = new char[10];
            int n;
            //Voy leyendo el flujo de entrada de datos y guardo cada lectura en bufI
            while ((n = miFileReader.read(bufI)) != -1) {
                bufO.append(bufI);
                //bufI = new char[10]; ;
            }
        }
        System.out.println(bufO);

    }
}
