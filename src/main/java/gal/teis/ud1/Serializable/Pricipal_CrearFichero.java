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
package gal.teis.ud1.Serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Esther Ferreiro
 */
public class Pricipal_CrearFichero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //crearFicheroSerializable();
        sobrescribirFicheroSerializable();
        leerFicheroSerializable();
    }

    static void crearFicheroSerializable() {
        //Definimos variable Persoa
        Persona amigo;
        //Declaramos o ficheiro.
        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File ficheiro = new File("ficheiropersoas.dat");
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
            //Tratar excepciones
        }

    }

    static void sobrescribirFicheroSerializable() {
        //Definimos variable Persoa
        Persona amigo;
        //Declaramos o ficheiro.
        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File ficheiro = new File("ficheiropersoas.dat");
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
            //Tratar excepciones
        }

    }

    static void leerFicheroSerializable() {
//        //Definimos variable Persoa
//        Persoa amigo;
//        //Declaramos o ficheiro.Se non existe, créao. Se existe sobreescribe.
//        File ficheiro = new File("ficheiropersoas.dat");
//        //Creamos un fluxo de entrada: ficheiro -> aplicación
//        try (FileInputStream miFIS = new FileInputStream(ficheiro);
//                ObjectInputStream miOIS = new ObjectInputStream(miFIS);) {
//            //Bucle para ler do ficheiro
//            while (true) {
//                amigo = (Persoa) miOIS.readObject();
//                //Visualizamos contido do ficheiro
//                System.out.println("Nome:" + amigo.getNome() + " Idade:" + amigo.getIdade());
//            }
//        } catch (EOFException e) {
//            System.out.println("Fin de fichero");
//        } catch (ClassNotFoundException |FileNotFoundException  e){
//            System.out.println("Se ha producido un error 1");
//        } catch (IOException e){
//            System.out.println("Se ha producido un error 2 "+ e.toString());
//        }
//Definimos un objeto de tipo Persoa
        Persona amigo;
        //Declaramos o ficheiro.Se non existe, créao.
        File ficheiro = new File("ficheiropersoas.dat");
        //Creamos un fluxo de entrada: ficheiro -> aplicación
        try (FileInputStream miFIS = new FileInputStream(ficheiro);
                ObjectInputStream miOIS = new ObjectInputStream(miFIS);) {
            //Bucle para ler do ficheiro
            //Cando chega ao fin de arquivo amigo é null!!!
            while ((amigo = (Persona) miOIS.readObject()) != null) {
                //Visualizamos contido do ficheiro
                System.out.println("Nome:" + amigo.getNome()
                        + "Idade:" + amigo.getIdade());
            }
        } catch (ClassNotFoundException | IOException e) {
            //Tratar excepcion
        }
    }
}
