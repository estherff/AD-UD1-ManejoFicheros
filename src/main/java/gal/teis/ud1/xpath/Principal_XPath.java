/*
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package gal.teis.ud1.xpath;

import gal.teis.ud1.dom.*;
import gal.teis.ControlData;
import gal.teis.Menu;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.SAXException;

/**
 *
 * Se crea un fichero con objetos de tipo Persona. Se consultan datos del
 * fichero de objetos como el tipo de objeto y sus propiedades. Se crear un
 * árbol DOM a partir del ficehro de objetos de tipo Persona Se muestra el árbol
 * DOM por pantalla con métodos específicos. Se recomienda probar la ejecución
 * con las consultas de
 * https://www.mclibre.org/consultar/xml/lecciones/xml-xpath.html#xpath-que-es
 *
 * @author Esther Ferreiro
 *
 * @version 1.2
 */
public class Principal_XPath {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        Document documento = null; //para manejar el DOM
        String nombreFichero = ""; //para alamcenar el nombre del fichero xml
        boolean finalizar = false; //para controlar la salida del programa
        File elFicheroObjetos = null; //para almacenar la instancia del fichero serializado de objetos
        String consultaXPath = "";//Para almacenar la consulta XPath sobre el fichero
        XPathExpression laXPathExpression = null; //La tengo que definir aquí porque controlo la exception
        boolean expresionCorrecta = true;

        //creo una instancia de la clase Scanner para la introdución de datos por teclado
        Scanner sc = new Scanner(System.in);

        /**
         * ********SELECCIÓN DEL FICHERO XML SOBRE EL QUE EJECUTAR LAS
         * CONSULTAS XPATH*****************
         */
        System.out.println("\n*****************"
                + "Seleccine el fichero para realizar consultas XPath");
        nombreFichero = seleccionarFicheroXML();
        elFicheroObjetos = new File(nombreFichero);
        /**
         * ********SE MUESTRA EL CONTENIDO DEL FICHERO CON UN ACCESO SECUENCIAL
         * MEDIANTE UN STREAM DE CARACTERES*****************
         */
        leeTodosLosCaracteres(elFicheroObjetos);
        /**
         * ********CCREA UN ARBOL DOM A PARTIR DE UN FICHERO
         * XML*****************
         */
        DocumentBuilder elDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        documento = elDocumentBuilder.parse(elFicheroObjetos);
        /*Crea un objeto XPath para poder hacer las consultas XPath*/
        XPath elXPath = XPathFactory.newInstance().newXPath();
        do {
            do {
                System.out.println("\n\n");
                System.out.println("Introduzca la consulta que dese realizar sobre el fichero XML. Introduzca un 0 para finalizar");
                consultaXPath = ControlData.lerString(sc);
                expresionCorrecta = true;
                /*Creo un objeto XPathExpression que es capaz de almacenar una consulta de búsqueda XPath que se pasa como un String*/
                try {
                    laXPathExpression = elXPath.compile(consultaXPath);
                } catch (XPathExpressionException e) {
                    System.out.println("La expresión no es correcta, vuelva a introducirla ");
                    expresionCorrecta = false;
                }
            } while (!expresionCorrecta && consultaXPath != "0");
            /*Una vez que hemos guardado la consulta en un objeto XPathExpression pasamos a ejecutarla sobre el Document DOM.
        Tenemos que tener en cuenta que el resultado podrán ser múltiples nodos por lo que tenemos que indicarle, con la 
        constante XPath.NODESET, que convierta el resultado de la consulta en un objeto que pueda ser casteado a NodeList
             */
            if (!consultaXPath.equals("0")) {
                NodeList resultadoConsulta = (NodeList) laXPathExpression.evaluate(documento, XPathConstants.NODESET);
                /*Recorremos el NodeList*/
                if (resultadoConsulta.getLength() > 0) {
                    System.out.println("El resultado de la consulta es:\n");
                    for (int i = 0; i < resultadoConsulta.getLength(); i++) {
                        //Extraer un nodo de la lista para consultar sus hijos
                        Node elNodo = resultadoConsulta.item(i);
                        tratarNodoRecursivo(elNodo);
                        //Cuando son solo atributos que salte de´línea
                        //p.e. /biblioteca/libro/autor/@fechaNacimiento
                        if (elNodo.getNodeType() == Node.ATTRIBUTE_NODE) {
                            System.out.println("");
                        }
                    }
                } else {
                    System.out.println("No hay resultados para esa consulta");
                }

            }
        } while (!consultaXPath.equals("0"));

        sc.close();

    }

    /**
     * Devuelve en array de File con aquellos ficheros que coincidan con las
     * extensiones que se pasan por parámetro. Por ejemplo: listFileByExtension*
     * ("C:\\ficheros\\", ".xml", ".java"); devolverá un File[] con los ficheros
     * de C:\Ficheros que tengan extensión xml o java.
     *
     * @param filePath path de la carpeta para listar archivos
     * @param extensiones extensiones por las que filtrar, es un parámetro
     * opcional que permite que se le pasen un número indeterminado de valores
     * String que el método recojerá como un array de String.
     * @return lista de archivos
     */
    public static File[] listarFicherosByExtension(String filePath, String... extensiones) {
        File[] listaOfFiles = new File[0];
        /* FilenameFilter es una interfaz que contiene únicamente el método
         public boolean accept (File dir, String name) y devuelve true:
         entonces el archivo se añade a la lista false: entonces no se añade
         el archivo a la lista El método accept se llama automáticamente al
         realizar file.listFiles(filtro)
         */
        FilenameFilter elFilenameFilter = new FilenameFilter() {
            /*Creamos una clase anónima y sobreescribimos el método accept cuya 
            signatura/firma está declarada en la interfaz FilenameFilter y que 
            debe devolver true para aquellos ficheros que cumplen la condición de 
            tener una extensión igual a las que se pasan por parámetro
            (String... extensiones)*/
            @Override
            public boolean accept(File dir, String nombre) {
                boolean filtrado = false;
                int indice = 0;
                while (indice < extensiones.length && !filtrado) {
                    if (nombre.toLowerCase().endsWith(extensiones[indice])) {
                        filtrado = true;
                    } else {
                        indice++;
                    }
                }
                return filtrado;
            }
        };
        //Se crea un objeto File con la ruta que se ha pasado por parámetro
        File elFile = new File(filePath);
        if (elFile.exists()) {//Si existe esa ruta/pathfilePath);
            /*listFiles aplica el filtro que se pasa por parámetro
            y crea un File[] con todos los ficheros de la ruta indicada
            que pasan el filtro*/
            listaOfFiles = elFile.listFiles(elFilenameFilter);
        }

        return listaOfFiles;
    }

    public static String seleccionarFicheroXML() {
        byte numFichero;
        boolean numFicheroCorrecto;
        /*
        List es una clase abstracta de la cual hereda ArrayList. Se tiene que 
        crear una instancia de List pues queremos convertir un array de Files a ArrayList
        y el metodo que nos permite hacer esta operación, Arrays.asList, devuleve una instancia List
         */
        List<File> losFicherosObjetos = new ArrayList<File>();

        ArrayList<String> losFicheros = new ArrayList<String>();

        /* LLamo al método listarFicherosByExtension para que me muestre los fichero con 
        extensión xml que hay en la carpeta del proyecto actual.
        Este método devuelve un arraya de File y con Arrays.asList lo convertimos en una
        lista de File (List<File>) que 
         */
        losFicherosObjetos = Arrays.asList(listarFicherosByExtension(".", "xml"));

        for (int i = 0; i < losFicherosObjetos.size(); i++) {
            losFicheros.add(losFicherosObjetos.get(i).getName());
        }
        /*La clase Menu permite imprimir el menú a partir de los datos de un ArrayList<String>
            y utilizar métodos para control de rango*/
        Menu miMenu = new Menu(losFicheros);
        System.out.println("Los fichero losFicheros con extensión xml que hay en la carpeta del proyecto son:");
        miMenu.printMenu();
        System.out.println("Introduzca el nº del fichero para realizar consultas XPath");

        do {
            /*La clase ControlData permite hacer un control de tipo leído*/
            numFichero = ControlData.lerByte(sc);
            numFicheroCorrecto = miMenu.rango(numFichero);
        } while (!numFicheroCorrecto);

        System.out.println("Ha seleccionado el fichero " + losFicherosObjetos.get(numFichero - 1).getName());
        return losFicherosObjetos.get(numFichero - 1).getName();
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
                Reader miFileReader = new FileReader(ficheroSecuencialTexto, Charset.forName("UTF-8"))) {
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
                Document doc = (Document) nodo;
                tratarNodoRecursivo(doc.getDocumentElement());
                break;

            case Node.ELEMENT_NODE:
                Element elemento = (Element) nodo;
                String nombre = elemento.getNodeName();
                System.out.print(nombre);
                NamedNodeMap atributos = elemento.getAttributes();
                if (atributos.getLength() != 0) {
                    for (int i = 0; i < atributos.getLength(); i++) {
                        tratarNodoRecursivo(atributos.item(i));
                    }
                }
                NodeList hijos = nodo.getChildNodes();
                if (hijos != null) {

                    for (int i = 0; i < hijos.getLength(); i++) {
                        tratarNodoRecursivo(hijos.item(i));
                    }
                }

                System.out.println(nombre);

                break;

            case Node.ATTRIBUTE_NODE:
                Attr atributo = (Attr) nodo;
                System.out.print(" " + atributo.getNodeName()
                        + " = " + atributo.getNodeValue());
                break;

            case Node.TEXT_NODE:
                Text texto = (Text) nodo;
                String elTexto = texto.getTextContent();
                System.out.print(elTexto.strip());
        }
    }
}
