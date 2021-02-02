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
package gal.teis.ud1.file;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_File {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File ruta = new File("D:\\IES\\Teis\\otro1");
        File f = new File(ruta, "datos.txt");
        System.out.println("1----------------");
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getParent());
        System.out.println(ruta.getAbsolutePath());
        System.out.println(ruta.getParent());
         System.out.println("2----------------");
        if (!f.exists()) {  //se comprueba si el fichero existe o no
            System.out.println("Fichero " + f.getName() + " no existe");
            if (!ruta.exists()) {  //se comprueba si la ruta existe o no
                System.out.println("El directorio " + ruta.getName() + " no existe");
                if (ruta.mkdir()) { //se crea la ruta. Si se ha creado correctamente
                    System.out.println("Directorio creado");
                    try {
                        if (f.createNewFile()) {  //se crea el fichero. Si se ha creado correctamente
                            System.out.println("Fichero " + f.getName() + " creado");
                        } else {
                            System.out.println("No se ha podido crear " + f.getName());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Principal_File.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    System.out.println("No se ha podido crear " + ruta.getName());
                }
            } else {  try {
                //si la ruta existe creamos el fichero
                if (f.createNewFile()) {
                    System.out.println("Fichero " + f.getName() + " creado");
                } else {
                    System.out.println("No se ha podido crear " + f.getName());
                }
                } catch (IOException ex) {
                    Logger.getLogger(Principal_File.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else { //el fichero existe. Mostramos el tamaño
            System.out.println("Fichero " + f.getName() + " existe");
            System.out.println("Tamaño " + f.length() + " bytes");
        }
    }

    }
    
