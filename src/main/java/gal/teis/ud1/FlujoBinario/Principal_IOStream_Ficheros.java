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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_IOStream_Ficheros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        crearFicheroBytesDeString();
    }

    static void crearFicheroBytesDeString() {
        try (OutputStream miFOS = new FileOutputStream("texto.txt")) {
            String texto = "sfsdfdsfdsrueba con ficheros binarios";
            //Convertimos String en bytes y los pasamos a un array
            byte codigos[] = texto.getBytes();
            miFOS.write(codigos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void leerFicheroBytes(){
        try(InputStream miFIS=new FileInputStream("texto.txt")){
            int valor=miFIS.read();
            while(valor!=1){
                System.out.print((char)valor);
                valor=miFIS.read();
                double a=.8_9;            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
