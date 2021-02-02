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
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
/**
 *
 * @author Esther Ferreiro Métodos para crear/modificar/leer un fichero
 * serializale de objetos de tipo Persona
 */
public class Lib_FicheroSerializablePersonas {

    /**
     * Crear un fichero serializable con objetos de tipo Persona y devuelve
     * una instancia File del mismo
     */
    public static File crearFicheroSerializable(String nombreFichero) {
        //Definimos variable Persoa
        Persona amigo;
        //Declaramos o ficheiro.
        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File ficheiro = new File(nombreFichero);
        //Creamos un fluxo de saída: aplicación -> ficheiro
        try (FileOutputStream miFOS = new FileOutputStream(ficheiro);
                ObjectOutputStream miOOS = new ObjectOutputStream(miFOS)) {
            //Definimos un array de nomes que queremos escribir no ficheiro
            String nomes[] = {"Antonio", "Rocio", "Cris", "José Luis", "Ana"};
            //Definimos un array de idades que acompañarán aos nomes
            int idades[] = {31, 28, 35, 33, 45};
            //Bucle para crear os obxectos e escribir no ficheiro
            for (int i = 0; i < nomes.length; i++) {
                //Creo un obxecto Persoa
                amigo = new Persona(nomes[i], idades[i]);
                //Almaceno os obxectos Persoa no ficheiro
                miOOS.writeObject(amigo);
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error 1" + e.toString());
        }
        return ficheiro;
    }

    /**
     * Modificar un fichero serializable con objetos de tipo Persona
     */
    static void sobrescribirFicheroSerializable(String nombreFichero) {
        //Definimos variable Persoa
        Persona amigo;
        //Declaramos o ficheiro.
        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File ficheiro = new File(nombreFichero);
        //Creamos un fluxo de saída: aplicación -> ficheiro
        try (FileOutputStream miFOS = new FileOutputStream(ficheiro, true);
                MiObjetoOutputStream miOOS = new MiObjetoOutputStream(miFOS)) {
            //Definimos un array de nomes que queremos escribir no ficheiro
            String nomes[] = {"Antonio", "Rocio", "Cris", "José Luis", "Ana"};
            //Definimos un array de idades que acompañarán aos nomes
            int idades[] = {31, 28, 35, 33, 45};
            //Bucle para crear os obxectos e escribir no ficheiro
            for (int i = 0; i < nomes.length; i++) {
                //Creo un obxecto Persoa
                amigo = new Persona(nomes[i], idades[i]);
                //Almaceno os obxectos Persoa no ficheiro
                miOOS.writeObject(amigo);
            }
        } catch (IOException e) {
            System.out.println("Se ha producido un error 2" + e.toString());
        }

    }

    /**
     * Mostrar por pantalla un fichero serializable con objetos de tipo Persona
     */
    static void leerFicheroSerializable(String nombreFichero) {
        //Definimos variable Persoa
        Persona amigo;
        //Declaramos o ficheiro.Se non existe, créao. Se existe sobreescribe.
        File ficheiro = new File(nombreFichero);
          
        System.out.println("\n");
        System.out.println("************************************************************");
        System.out.println("SE MUESTRA DEL FICHERO SERIALIZADO DE OBJETO DE TIPO PERSONA");
        //Creamos un fluxo de entrada: ficheiro -> aplicación
        try (FileInputStream miFIS = new FileInputStream(ficheiro);
                ObjectInputStream miOIS = new ObjectInputStream(miFIS);) {
            //Bucle para ler do ficheiro ata que se produza unha excepcion EOFException
            while (true) {
                amigo = (Persona) miOIS.readObject();
                System.out.println(miOIS.readObject().getClass().getCanonicalName());

                //Visualizamos contido do ficheiro
                System.out.println("Nombre:" + amigo.getNome() + " Edad:" + amigo.getIdade());
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException | FileNotFoundException e) {
            System.out.println("Se ha producido un error 3");
        } catch (IOException e) {
            System.out.println("Se ha producido un error 4" + e.toString());
        }
    }

    /**
     * Muestra por pantalla el la clase del objeto almacenado en el fichero que se pasa por parámetro
     * También muestra las propiedades de dicho objeto
     * 
     * @param nombreFichero Tipo String Nombre del fichero a tratar
     */
    static void mostrarDatosObjetoFicheroSerializable(File elFicheroObjetos) {
        //Declaramos o ficheiro.Se non existe, créao. Se existe sobreescribe.

        try (FileInputStream miFIS = new FileInputStream(elFicheroObjetos);
                ObjectInputStream miOIS = new ObjectInputStream(miFIS);) {
            //Accedo a un objeto del fichero para conocer el tipo de objeto y sus propiedades
            Class _class = miOIS.readObject().getClass();
            System.out.println("\nEl fichero tiene objetos de tipo " + _class.getCanonicalName());
            //Muestro las propiedades del objeto
            Field[] _propiedades = _class.getDeclaredFields();
            /*propiedades[i].getName --> obtenemos el nombre de la propiedad
              propiedades[i].getType --> obtenemos una cadena con el tipo de datos
             si es un objeto primitivo, sino, podremos obtener la clase en el Classpath*/
            System.out.println("Los campos de la clase Persona son:");
            for (Field campo : _propiedades) {
                System.out.println("\t" + campo);
            }
            System.out.println();
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (ClassNotFoundException | FileNotFoundException e) {
            System.out.println("Se ha producido un error 3");
        } catch (IOException e) {
            System.out.println("Se ha producido un error 4" + e.toString());
        }
    }
}
