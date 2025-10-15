package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class GestionSaxlibros extends DefaultHandler {

    private String elementoActual = "";
    private String nombre = "";
    private String autor = "";
    private double precio = 0.0;
    private double totalPedido = 0.0;

    public GestionSaxlibros(){
        super();
    }
    public void startDocument(){
        System.out.println("--------Factura---------");
    }
    public void endDocument(){
        System.out.printf("Precio total del pedido: %.2f €\n", totalPedido);
    }
    public void startElement(String url, String nombre, String qName, Attributes attributes){
        elementoActual = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("libro")) {
            double precioConIva = precio * 1.04; // 4% de incremento
            System.out.printf("Libro: %s\nAutor: %s\nPrecio: %.2f €\nPrecio con 4%%: %.2f €\n",
                    nombre, autor, precio, precioConIva);
            System.out.println("----------------------------------");
            totalPedido += precioConIva;


            nombre = "";
            autor = "";
            precio = 0.0;
        }
        elementoActual = "";
    }
    public void characters(char[] ch, int inicio, int longitud){
        String caracter = new String(ch,inicio,longitud);
        caracter=caracter.replaceAll("[\t\n]", "limpio");
        System.out.printf("\t Caracteres: %s\n",caracter);
    }
}
