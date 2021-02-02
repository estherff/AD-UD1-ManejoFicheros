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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Esther Ferreiro
 */
public class ManejadorEventosSAX extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Comienza el parser SAX del documento XML");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Finaliza el parser SAX del documento XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.printf("\tComienza parse de elemento: %s %n", qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.printf("\tFinaliza parse de elemento: %s %n", qName);
    }

    @Override
    /**
     * Pasa todo el XML como char[] e indica con start el índice del array
     * donde comienza el contenido de la etiqueta y con length su longitud
     * Cuando la etiqueta no tiene texto => length es 1
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (length > 1) {
            //Transforma un array de caracteres en un String
            String texto = new String(ch, start, length);
            //Eliminar los saltos de línea
            texto = texto.replaceAll("[\t\n]", "");
            System.out.printf("\t\tEl contenido es : %s %n", texto);
        }

    }
}
