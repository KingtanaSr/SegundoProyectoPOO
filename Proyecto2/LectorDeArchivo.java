package Proyecto2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import static java.lang.System.exit;

public class LectorDeArchivo {
    private final String rutaArchivo;
    private final Path path;
    private String contenidoArchivo = "";
    public LectorDeArchivo(LectorDeEntrada lector) {

        System.out.print("Introduce la ruta del archivo: ");
        rutaArchivo = lector.leerCadena(); // se lee el nombre del archivo
        if(!esTxT(rutaArchivo)){
            System.out.println("El formato ingresado no es .txt");
        }
        path = Paths.get(rutaArchivo); // se convierte en ruta
        try {
            contenidoArchivo = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }finally {
            System.out.print("");
        }
    }
    //método para verificar que el archivo es .txt
    private static boolean esTxT(String ruta) { //recibe la ruta que ingresó el usuario
        File archivo = new File(ruta); //se crea un objeto tipo 'File'
        String extension = obtenerExtension(archivo);
        return extension != null && extension.equals("txt"); // se retorna 'true' si no es nulo y si la extension es igual a 'txt'.
    }

    //método auxiliar que retorna la extensión de un archivo
    private static String obtenerExtension(File archivo) { // obtiene un archivo como parámetro
        String nombreArchivo= archivo.getName(); // se guarda el nombre del archivo
        int punto = nombreArchivo.lastIndexOf('.'); // se guarda el índice del carácter '.'
        if (punto != -1 && punto < nombreArchivo.length() - 1) { //si el índice del punto es diferente a -1 y es menor al length del nombre del archivo
            return nombreArchivo.substring(punto + 1).toLowerCase(); // se retorna la extensión, en minúsculas
        }
        return null; // sino, se retorna nulo
    }

    //método que tiene el objetivo añadir '<BOS>' y '<EOS>' al inicio de cada párrafo
    public void modificarContenido(int tam) {
        String[] lineas = contenidoArchivo.split("\\R"); // se establece que se divida cada cambio de línea

        StringBuilder contenidoModificado = new StringBuilder();

        for (String linea : lineas) { // por cada línea en la listas de líneas
            for (int i = 0; i < tam; i++) {
                contenidoModificado.append("<BOS> "); //se añade "<BOS>"
            }
            contenidoModificado.append(linea);
            for (int i = 0; i < tam; i++) {
                contenidoModificado.append(" <EOS>"); //se añade "<EOS>"
            }
            contenidoModificado.append("\n");
        }

        contenidoArchivo = contenidoModificado.toString().trim();

    }

    public String getContenidoArchivo() {
        return contenidoArchivo;
    }

}