package Proyecto2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizador {
    private final LectorDeArchivo lectorDeArchivo;
    private final String contenido;
    public List<String> listaTokens;

    public Tokenizador(LectorDeArchivo lectorDeArchivo) {
        this.lectorDeArchivo = lectorDeArchivo;
        this.contenido = lectorDeArchivo.getContenidoArchivo();
        this.listaTokens = guardarTokens(contenido);
    }

    // método para guardar los tokens, recibe el contenido del archivo
    public List<String> guardarTokens(String contenido) {
        List<String> tokens = new ArrayList<>(); // lista para almacenar los tokens
        StringTokenizer st = new StringTokenizer(contenido, " []()*&^%$#+@-,:';¿¡!?.\n\r", true); // usando la biblioteca "StringTokenizer", se establece los límites de los tokens
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            token = token.trim(); // en este while se verifica que hay más tokens, se recorre y se guarda en la lista mientras que el token no es vacío
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }

}
