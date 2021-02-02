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
package gal.teis.ud1.dom;

import gal.teis.ud1.Serializable.*;
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
