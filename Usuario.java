package org.example;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int codUsuario;
    private String aficiones;

    public Usuario(String aficiones) {
        this.aficiones = aficiones;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public String getAficiones() {
        return aficiones;
    }

    public void setAficiones(String aficiones) {
        this.aficiones = aficiones;
    }

    @Override
    public String toString() {
        return "U" + codUsuario + " " + aficiones;
    }
}