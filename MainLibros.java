package org.example;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class MainLibros {
    public static void main(String[] args) throws SAXException, IOException {

        XMLReader procesador = XMLReaderFactory.createXMLReader();
        GestionSaxlibros gestionSaxlibros = new GestionSaxlibros();
        procesador.setContentHandler(gestionSaxlibros);

        InputSource fichero = new InputSource("pedidos.xml");

        procesador.parse(fichero);

    }
}