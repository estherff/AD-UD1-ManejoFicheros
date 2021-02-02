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
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 */
public class Principa_IOStream_Imagen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            lecturaFlujoBinario_Imagen();
            //lecturaEscrituraFlujoBinario_Imagen();
           
    }

    static void lecturaFlujoBinario_Imagen() {
        int dato;
        try (InputStream miFIS = new FileInputStream(Paths.get("fondo.png")
                .toFile())) {
            //leo byte a byte
            while ((dato = miFIS.read()) != -1) {
                //Imprime el Código binario
                System.out.print((char) dato);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static void lecturaEscrituraFlujoBinario_Imagen() {

        int tamano;
        try (InputStream miFIS
                = new FileInputStream(Paths.get("fondo.jpg")
                        .toFile());
                OutputStream miFOS
                = new FileOutputStream(Paths.get("fondo1.jpg")
                        .toFile());) {
            byte[] buf = new byte[1024];
            while (miFIS.read(buf) != -1) {
                miFOS.write(buf);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    
}
