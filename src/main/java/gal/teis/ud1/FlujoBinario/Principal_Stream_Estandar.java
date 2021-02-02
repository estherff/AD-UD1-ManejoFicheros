/*
 *
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
package gal.teis.ud1.FlujoBinario;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.PrintStream;


/**
 *
 * @author Esther Ferreiro
 */
public class Principal_Stream_Estandar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //lecturaTecladoFlujoBinario();
        //dirigirErroresAFichero();
        dirigirErroresAFichero_Modificado();
    }

    static void lecturaTecladoFlujoBinario() {
        int c;
        int contador = 0;
        try {
            //Leer caracter a caracter hasta fin de línea
            while ((c = System.in.read()) != '\n') {
                contador++;
                System.out.println((char) c);
            }
            System.out.println("Se han introducido " + contador + " caracteres");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void flujoEstandarEntradaFichero() {
//        try {
//            //Crear dos flujos de salida hacia sendos ficheros
//            InputStream fout = new FileInputStream("stdout.log");
//           
//            //Crar dos objetos PrintStrema con los flujos de salida creados
//            PrintStream miOut = new PrintStream(fout);
//            PrintStream miErr = new PrintStream(ferr);
//            //Dar valor a las propiedades out y err de System que conforman
//            //los flujos de salida estándar y de error
//            System.setOut(miOut);
//            System.setErr(miErr);
//            //Envío un string al flujo de salida estándar mediante el objeto creado
//            miOut.println("trace console 1");
//            //Envío un string al flujo de salida estándar que está modificado
//            //ya que out tiene el valor de miOut (System.setOut(miOut);)
//            System.out.println("trace console 2");
//            //Provoco una excepción que será capturada por catch
//            int a = 100 / 0;
//        } catch (Exception ex) {
//            //Envío el valor de la excepción a System.err cuyo destino del flujo
//            //está modificado
//            System.err.println(ex.toString());
//        }
    
    }

    static void dirigirErroresAFichero() {
        try {
            //Crear dos flujos de salida hacia sendos ficheros
            FileOutputStream fout = new FileOutputStream("stdout.log");
            FileOutputStream ferr = new FileOutputStream("stderr.log");
            //Crar dos objetos PrintStrema con los flujos de salida creados
            PrintStream miOut = new PrintStream(fout);
            PrintStream miErr = new PrintStream(ferr);
            //Dar valor a las propiedades out y err de System que conforman
            //los flujos de salida estándar y de error
            System.setOut(miOut);
            System.setErr(miErr);
            //Envío un string al flujo de salida estándar mediante el objeto creado
            miOut.println("trace console 1");
            //Envío un string al flujo de salida estándar que está modificado
            //ya que out tiene el valor de miOut (System.setOut(miOut);)
            System.out.println("trace console 2");
            //Provoco una excepción que será capturada por catch
            int a = 100 / 0;
        } catch (Exception ex) {
            //Envío el valor de la excepción a System.err cuyo destino del flujo
            //está modificado
            System.err.println(ex.toString());
        }

    }
    
    static void dirigirErroresAFichero_Modificado() {
        try {
            //Crear un flujos de salida hacia el fichero salida.txt
            FileOutputStream fout = new FileOutputStream("salida.txt");
 
            //Crar dos objetos PrintStrema con los flujos de salida creados
            PrintStream miOut = new PrintStream(fout);
            //Dar valor a las propiedades out y err de System que conforman
            //los flujos de salida estándar y de error
            System.setErr(System.out);
            System.setOut(miOut);
            
            //Envío un string al flujo de salida estándar mediante el objeto creado
            miOut.println("trace console 1");
            //Envío un string al flujo de salida estándar que está modificado
            //ya que out tiene el valor de miOut (System.setOut(miOut);)
            System.out.println("trace console 2");
            //Provoco una excepción que será capturada por catch
            int a = 100 / 0;
        } catch (Exception ex) {
            //Envío el valor de la excepción a System.err cuyo destino del flujo
            //está modificado
            System.err.println(ex.toString());
        }

    }
    

}
