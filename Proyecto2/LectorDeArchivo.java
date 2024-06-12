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
        rutaArchivo = lector.leerCadena();

        path = Paths.get(rutaArchivo);
        try {
            contenidoArchivo = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void modificarContenido(int tam) {
        String[] lineas = contenidoArchivo.split("\\R");

        StringBuilder contenidoConBOSyEOS = new StringBuilder();

        for (String linea : lineas) {
            for (int i = 0; i < tam; i++) {
                contenidoConBOSyEOS.append("<BOS> ");
            }
            contenidoConBOSyEOS.append(linea);
            for (int i = 0; i < tam; i++) {
                contenidoConBOSyEOS.append(" <EOS>");
            }
            contenidoConBOSyEOS.append("\n");
        }

        contenidoArchivo = contenidoConBOSyEOS.toString().trim();

    }

    public String getContenidoArchivo() {
        return contenidoArchivo;
    }

}