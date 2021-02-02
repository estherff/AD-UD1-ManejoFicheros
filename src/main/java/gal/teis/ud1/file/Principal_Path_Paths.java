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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_Path_Paths {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println("Información de la ruta actual");
//        Path miPathRelative = Paths.get(".");
//        Path miPathAbsolute = miPathRelative.toAbsolutePath().normalize();
//
//        System.out.printf("Ruta relativa: %s\n", miPathRelative);
//        System.out.printf("Ruta absoluta: %s\n", miPathAbsolute);
//        System.out.printf("Carpetas que componen la ruta absoluta: %d\n", miPathAbsolute.getNameCount());
//        System.out.printf("Carpeta padre: %s\n", miPathAbsolute.getParent());
//        System.out.printf("Subcarpetas de la ruta (0, 2): %s\n", miPathAbsolute.subpath(0, 2));
//
//        boolean creado = false;
//
//        File miFichero = new File("mifichero.txt");
//        try {
//            creado = miFichero.createNewFile();
//        } catch (IOException ex) {
//            System.out.println(ex.toString());
//        }
//
//        Path ruta1 = Paths.get("mifichero.txt");
//
//        System.out.println(ruta1.getFileName());
//        System.out.println(ruta1.toAbsolutePath());
//
//        Path ruta2 = ruta1.toAbsolutePath();
//        int x=3;
//
//        for (Path miniruta : ruta2) {
//            System.out.println(miniruta);
//        }

//        Path rutaOrigen = Paths.get("mifichero.txt");
//        Path rutaDestino = Paths.get("D:\\IES\\6_Teis\\eldestino.txt");
//        System.out.println("La ruta origen es " + rutaOrigen);
//        System.out.println("La ruta destino es " + rutaDestino);
//        try {
//            //Copia ficheros de disco
//            Files.copy(rutaOrigen, rutaDestino);
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
diego();
    }

    static void diego() {
        Path ruta = Paths.get("diego.txt");

        Path rutaDestino = Paths.get("D:\\");

        try {
            if (Files.notExists(ruta)) {
                Files.createFile(ruta);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
//            Files.createFile(rutaDestino);
            Files.copy(ruta, rutaDestino);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
