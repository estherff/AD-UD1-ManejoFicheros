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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_InputStream_Reader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    /**
     * Crea un flujo de lectura de texto a partir de un flujo binario
     */
    static void crearReaderAPartirDeFileInputStream() {
       
        String linea=null;
        //Se toma un flujo de bytes como parámetro para crear un InputStreamReader
        try (InputStream miFIS = new FileInputStream(Paths.get("poema.txt").toFile())) {
            InputStreamReader miISR = new InputStreamReader(miFIS);
            //Se crea un BufferedReader a partir del InputStreamReader
            BufferedReader miBR=new BufferedReader(miISR);
            //Se lee el buffer línea a línea y se muestra por pantalla
            while ((linea=miBR.readLine())!=null){
                System.out.println(linea);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
