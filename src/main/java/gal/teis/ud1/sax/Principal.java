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
package gal.teis.ud1.sax;

/**
 *
 * @author Esther Ferreiro
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //1. Crear un objeto procesador XML de tipo XMLReader
            //Este proceso puede provocar la SAXException
            SAXParserFactory elparserFactory = SAXParserFactory.newDefaultInstance();
            SAXParser elparserSAX = elparserFactory.newSAXParser();
            XMLReader elXMLReader = elparserSAX.getXMLReader();
            
            //2. Crear una instancia de la clase que hemos creado para manejar los eventos SAX
            //La clase hereda de DefaultHandler   
            ManejadorEventosSAX miManejadorEventos = new ManejadorEventosSAX();
            
            //3. Enlazar el parser con el manejador de eventos que se va a utilizar
            elXMLReader.setContentHandler(miManejadorEventos);
            
            //4. Crear un InputSource a partir del fichero XML a procesar
            InputSource elficheroXMLInputSource = new InputSource ("alumnado.xml");
            
            //5. Enlazar el parser con el objeto que representa al fichero que se va a procesar 
            elXMLReader.parse(elficheroXMLInputSource);
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
