package org.example;

import java.io.*;
import java.util.*;

public class FicheroLecturaEscritura {
    public static String ruta = "C:\\Users\\ismat\\IdeaProjects\\FicheroPersonass\\src\\DatosConFicheros\\usuarios.txt";
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("======MENÚ PRINCIPAL=====");
            System.out.println("1. Añadir usuario.");
            System.out.println("2. Mostrar usuarios introducidos.");
            System.out.println("3. Generar fichero de concordancias.");
            System.out.println("0. Salir.");
            System.out.println("=========================");

            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion) {
                case 1 -> anadirUsuario();
                case 2 -> mostrarUsuarios();
                case 3 -> generarFicheroConcordancias();
                default -> System.out.println("Saliendo...");
            }

        } while (opcion != 0);
    }

    public static void anadirUsuario() {
        System.out.print("Nuevas aficiones: ");
        String aficiones = sc.nextLine();

        int siguienteCodigo = calcularSiguienteCodigo();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write("U" + siguienteCodigo + " " + aficiones);
            bw.newLine();
            System.out.println("Usuario añadido correctamente");
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }

    private static int calcularSiguienteCodigo() {
        File fichero = new File(ruta);
        if (!fichero.exists() || fichero.length() == 0) {
            return 100;
        }

        int contador = 100;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            while (br.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Error al calcular código: " + e.getMessage());
        }
        return contador;
    }

    public static void mostrarUsuarios () {
        File fichero = new File(ruta);
        if (!fichero.exists() || fichero.length() <= 0) {
            System.out.println("El fichero no existe.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (EOFException e) {
            System.out.println("Fin de fichero");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        }
    }

    public static void generarFicheroConcordancias() {
        List<String> codigos = new ArrayList<>();
        List<List<String>> aficionesUsuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Jhon\\IdeaProjects\\AccesoDatosFicheros\\src\\main\\java\\org\\example\\usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                if (partes.length > 1) {
                    codigos.add(partes[0]);
                    List<String> aficiones = new ArrayList<>();
                    for (int i = 1; i < partes.length; i++) {
                        aficiones.add(partes[i]);
                    }
                    aficionesUsuarios.add(aficiones);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        List<String> concordancias = new ArrayList<>();
        for (int i = 0; i < codigos.size(); i++) {
            for (int j = i + 1; j < codigos.size(); j++) {
                List<String> comunes = new ArrayList<>();
                for (String aficion : aficionesUsuarios.get(i)) {
                    if (aficionesUsuarios.get(j).contains(aficion)) {
                        comunes.add(aficion);
                    }
                }

                if (!comunes.isEmpty()) {
                    Collections.sort(comunes);
                    String linea = codigos.get(i) + " " + codigos.get(j) + " " + String.join(" ", comunes);
                    concordancias.add(linea);
                }
            }
        }

        if (concordancias.isEmpty()) {
            System.out.println("No hay aficiones comunes entre los usuarios.");
        } else {
            try (PrintWriter pw = new PrintWriter(new FileWriter("concordancias.txt"))) {
                for (String concordancia : concordancias) {
                    pw.println(concordancia);
                }
            } catch (IOException e) {
                System.out.println("Error al crear el fichero de salida: " + e.getMessage());
                return;
            }

            System.out.println("Fichero 'concordancias.txt' creado con " + concordancias.size() + " parejas de usuarios con aficiones comunes.");
        }
    }
}
