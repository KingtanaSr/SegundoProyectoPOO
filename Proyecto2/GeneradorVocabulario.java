package Proyecto2;

import java.util.*;

public class GeneradorVocabulario {
    private final Tokenizador tokenizador;
    private final LectorDeArchivo lectorDeArchivo;

    public GeneradorVocabulario(Tokenizador tokenizador, LectorDeArchivo lectorDeArchivo) {
        this.lectorDeArchivo = lectorDeArchivo;
        this.tokenizador = tokenizador;
    }

    // método para generar el vocabulario relacionado al texto dado
    public List<String> generarVocabulario() {
        List<String> tokens = tokenizador.guardarTokens(lectorDeArchivo.getContenidoArchivo());
        Set<String> set = new LinkedHashSet<>(tokens);
        set.remove("<EOS>"); // se eliminan los EOS Y BOS porque no son palabras validas en el vocabulario
        set.remove("<BOS>");
        return new ArrayList<>(set);
    }
}

