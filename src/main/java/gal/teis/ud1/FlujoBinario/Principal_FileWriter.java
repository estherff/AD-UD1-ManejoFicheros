/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
