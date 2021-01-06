/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.teis.ud1.Serializable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Redefinición de la clase ObjectOuputStream para que no escriba una cabecera
 * al inicio del Stream. Para no tener problemas con las cabeceras de los
 * objetos y evitar el error StreamCorruptedException, creamos una clase con
 * nuestro propio ObjectOutputStream, heredando del original y redefiniendo el
 * método writeStreamHeader() vacío, para que no haga nada.
 */

public class MiObjetoOutputStream extends ObjectOutputStream {
     
     /**
     * Constructores
     */
 
    public MiObjetoOutputStream() throws IOException{
        
    }
    
    public MiObjetoOutputStream(OutputStream out) throws IOException, SecurityException{
        super(out);
    }
    
    /**
     * Redefinición del método de escribir la cabecera para que no haga nada.
     */
    @Override
    protected void writeStreamHeader()throws IOException{
        //No se hace naca
    }
}
