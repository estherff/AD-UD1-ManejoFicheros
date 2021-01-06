/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
