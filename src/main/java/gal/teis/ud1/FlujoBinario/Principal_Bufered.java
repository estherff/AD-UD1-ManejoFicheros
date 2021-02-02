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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;

/**
 *
 * @author Esther Ferreiro
 */
public class Principal_Bufered {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //leerBufferInputStream();
        //lecturaEscrituraBufferFlujoBinario_Imagen();
        lecturaEscrituraBufferFlujoTexto_Texto();
    }

    /**
     * Lee un fichero binario utilizando BufferInputStream
     */
    static void leerBufferInputStream() {
        long startTime = System.nanoTime();
        File fichero = Paths.get("foto.jpg").toFile();

        try (BufferedInputStream miBIS = new BufferedInputStream(new FileInputStream(fichero))) {
            int data = 0;
            while ((data = miBIS.read()) != -1) {
                //System.out.print((char) data);
                //data=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time = System.nanoTime() - startTime;
        System.out.println("El tiempo transcurrido es " + time);

    }

    /**
     * Lee y escribe un fichero binario utilizando 
     * BufferInputStream y BufferOutputStream
     */
    static void lecturaEscrituraBufferFlujoBinario_Imagen() {

        int dato;
        try (BufferedInputStream miBIS = new BufferedInputStream(
                new FileInputStream(Paths.get("fondo.jpg").toFile()));
            BufferedOutputStream miBOS = new BufferedOutputStream(
                new FileOutputStream(Paths.get("fondoCopia.jpg").toFile()));)
        {

            while ((dato = miBIS.read()) != -1) {
                miBOS.write(dato);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * Lee y escribe un fichero de texto utilizando 
     * BufferReader y Buffer
     */
    static void lecturaEscrituraBufferFlujoTexto_Texto() {
        int dato;
        //Objeto File con el fichero de texto
        File miFilesOrigen= Paths.get("poesia.txt").toFile();
        File miFilesDestino= Paths.get("poesiaCopia.txt").toFile();
        //Objeto Reader para leer el fichero de texto en UT-8
        try(BufferedReader mBR = new BufferedReader(
                new FileReader(miFilesOrigen,Charset.forName("UTF-8")));
                BufferedWriter mBW= new BufferedWriter(
                        new FileWriter(miFilesDestino, Charset.forName("UTF-8")))){
        //Leo bufer a bufer hasta el final de fichero
        while ((dato=mBR.read())!=-1) {
            //Agrego a la cadena  los caracteres leídos
            mBW.write(dato);
        }
        }catch (FileNotFoundException e){
            //Tratar la excepción
        }catch (IOException e){
            //Tratar la excepción
        }
    } 
}
