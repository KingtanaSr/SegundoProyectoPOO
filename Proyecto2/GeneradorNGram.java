package Proyecto2;

import java.util.ArrayList;
import java.util.List;

public class GeneradorNGram {
    private final Tokenizador tokenizador;

    private final Ngram ngram;

    public GeneradorNGram(Tokenizador tokenizador, Ngram ngram) {
        this.tokenizador = tokenizador;
        this.ngram = ngram;
    }

    public List<List<String>> generarNGram() {
        int n = ngram.getTamañoNgram();

        List<String> tokens = tokenizador.getListaTokens();

        for (int i = 0; i < n - 1; i++) {
            tokens.add("<EOS>");
            tokens.addFirst("<BOS>");
        }

        List<List<String>> nGrams = new ArrayList<>();

        if (tokens.size() >= n) {
            for (int i = 0; i <= tokens.size() - n; i++) {
                List<String> nGram = new ArrayList<>();
                for (int j = i; j < i + n; j++) {
                    nGram.add(tokens.get(j));
                }
                nGrams.add(nGram);
            }
        } else {
            System.out.println("La longitud de la lista de tokens es menor que n.");
        }

        return nGrams;
    }

}
