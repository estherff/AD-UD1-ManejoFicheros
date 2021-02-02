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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_FileReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            leeTodosLosCaracteres();
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
        
    
    
    /**
     * Lectura de todos los caracteres de un fichero.
     */

    static void leeTodosLosCaracteres() throws IOException {
        //Objeto File con el fichero de texto
        File miFiles= Paths.get("poesia.txt").toFile();
        //Objeto para almacenar los caracteres leídos del fichero
        StringBuilder miSB = new StringBuilder();
        //Objeto Reader para leer el fichero de texto en UT-8
        Reader miFileReader = new FileReader(miFiles,Charset.forName("UTF-8"));
        //fuffer de char para ir leyendo del fichero
        char[] bufer = new char[1024];
        //Leo bufer a bufer hasta el final de fichero
        while (miFileReader.read(bufer)!=-1) {
            //Agrego a la cadena  los caracteres leídos
            miSB.append(bufer);
        }
        //Cierro el fichero
        miFileReader.close();
        //Muestro por pantalla la cadena, resultado de leer el fichero
        System.out.println(miSB.toString());

    }  
}
