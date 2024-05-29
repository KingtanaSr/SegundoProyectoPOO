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
        this.listaTokens = guardarTokens();
    }

    private List<String> guardarTokens() {
        List<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(contenido, " []()*&^%$#+@-,:';¿¡!?.\n\r", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            token = token.trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        return tokens;
    }
    public List<String> getListaTokens() {
        return listaTokens;
    }
}
