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
package gal.teis.ud1.flujoCaracter;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 */
public class Principa_Reader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            leeTodosLosCaracteres();
        } catch (IOException ex) {
            Logger.getLogger(Principa_Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *
     * Lectura de todos los caracteres de un fichero.

     *
     * @throws IOException si hay problemas al abrir o al escribir.
     *
     */
    static void leeTodosLosCaracteres()
            throws IOException {
        //Como buffer voy a utilizar un StringBuilder 
        //que permite almacenar una cadena
        StringBuilder bufO = new StringBuilder();
        //Creo un FileReader a partir de un fichero de texto y codifico cada carazter con UTF-8
        try (
               Reader miFileReader = new FileReader(Paths.get("mifichero.txt").toFile(),Charset.forName("UTF-8"))) {
            //Creo un búfer de caracteres para los datos de entrada
            char[] bufI = new char[10];            
            int n;
            //Voy leyendo el flujo de entrada de datos y guardo cada lectura en bufI
            while ((n = miFileReader.read(bufI))!=-1) {
                bufO.append(bufI);
                //bufI = new char[10]; ;
            }
        }
        System.out.println(bufO); 

    }
    
}
