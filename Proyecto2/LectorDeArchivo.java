package Proyecto2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
public class LectorDeArchivo {
    private final String rutaArchivo;
    private final Path path;
    private String contenidoArchivo = "";
    public LectorDeArchivo(Lector lector) {

        System.out.print("Introduce la ruta del archivo: ");
        rutaArchivo = lector.leerCadena();

        path = Paths.get(rutaArchivo);
        try {
            contenidoArchivo = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String getContenidoArchivo() {
        return contenidoArchivo;
    }

}