/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
