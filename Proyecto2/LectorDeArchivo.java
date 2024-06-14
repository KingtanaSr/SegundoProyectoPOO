package Proyecto2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
public class LectorDeArchivo {
    private final String rutaArchivo;
    private final Path path;
    private String contenidoArchivo = "";
    public LectorDeArchivo(LectorDeEntrada lector) {

        System.out.print("Introduce la ruta del archivo: ");
        rutaArchivo = lector.leerCadena(); // se lee el nombre del archivo

        path = Paths.get(rutaArchivo); // se convierte en ruta
        try {
            contenidoArchivo = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //método que tiene el objetivo añadir "<BOS>" y "<EOS>" al inicio de cada párrafo
    public void modificarContenido(int tam) {
        String[] lineas = contenidoArchivo.split("\\R"); // se establece que se divida cada cambio de línea

        StringBuilder contenidoModificado = new StringBuilder(); // se crea un objeto de tipo StringBuilder

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