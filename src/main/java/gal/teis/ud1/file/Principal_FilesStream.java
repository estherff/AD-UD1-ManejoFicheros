/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_FilesStream {

    /**
     * Filtrar unos ficheros y la carpeta e imprimir
        los ficheros filtrados en mayúscula
     */
    public static void main(String[] args) {
        //Apunto a la carpeta donde están los ficheros
        Path ruta=Paths.get("ficheros_fuente");
        //Usar la 
        //Files.list(ruta);
        
        
    }
    
    int duplicar (int numero){
        return numero*2;
    }
    
}
