/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
