package Proyecto2;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Tokenizador {
    private final LectorDeArchivo lector;
    private final String contenido;
    public List<String> listaTokens;
    public Tokenizador(LectorDeArchivo lector) {
        this.lector = lector;
        this.contenido = lector.getContenidoArchivo();
        this.listaTokens = new ArrayList<>();
    }

    public void guardarTokens() {
        StringTokenizer st = new StringTokenizer(contenido, " []()*&^%$#+@-,:';¿¡!?.", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            token = token.replace("\n", "").replace("\r", "");
            if ((!token.equals(" ")&&(!token.trim().isEmpty()))) {
                listaTokens.add(token);
            }
        }
    }
    public List<String> getListaTokens() {
        return listaTokens;
    }
}

