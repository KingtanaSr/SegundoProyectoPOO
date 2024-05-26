package Proyecto2;

import java.util.*;

public class GeneradorVocabulario {
    private final Tokenizador tokenizador;

    public GeneradorVocabulario(Tokenizador tokenizador) {
        this.tokenizador = tokenizador;
    }

    public List<String> generarVocabulario() {
        List<String> tokens = tokenizador.getListaTokens();
        Set<String> set = new LinkedHashSet<>(tokens);
        return new ArrayList<>(set);
    }

}

