package Proyecto2;

import java.util.*;

public class GeneradorVocabulario {
    private final Tokenizador tokenizador;
    private final LectorDeArchivo lectorDeArchivo;

    public GeneradorVocabulario(Tokenizador tokenizador, LectorDeArchivo lectorDeArchivo) {
        this.lectorDeArchivo = lectorDeArchivo;
        this.tokenizador = tokenizador;
    }

    public List<String> generarVocabulario() {
        List<String> tokens = tokenizador.guardarTokens(lectorDeArchivo.getContenidoArchivo());
        Set<String> set = new LinkedHashSet<>(tokens);
        return new ArrayList<>(set);
    }

}

