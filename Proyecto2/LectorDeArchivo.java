package Proyecto2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Scanner;
public class LectorDeArchivo {
    private final String rutaArchivo;
    private final Path path;
    private String contenidoArchivo = "";
    public LectorDeArchivo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la ruta del archivo: ");
        rutaArchivo = scanner.nextLine();

        path = Paths.get(rutaArchivo);
        try {
            contenidoArchivo = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
    public String getContenidoArchivo() {
        return contenidoArchivo;
    }

}
