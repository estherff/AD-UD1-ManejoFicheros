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

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_FileWriter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            escribeCaracteres();
        } catch (IOException ex) {
            Logger.getLogger(Principal_FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Escritura de caracteres en ficheros.

    * @throws IOException salta si hay problemas
    * al abrir o al escribir.
     */
    static void escribeCaracteres()
            throws IOException {

        Writer miFW = new FileWriter(Paths.get("poesia.txt").toFile(), true);
        String cadena = "\n Poema de Rosalía de Castro";
        miFW.write(cadena);

        miFW.close();
    }
}
