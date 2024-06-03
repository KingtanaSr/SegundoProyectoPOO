package Proyecto2;

import java.util.*;

public class GeneradorTextoDeterminístico extends GeneradorTexto{
    public GeneradorTextoDeterminístico(SelectorGeneración selectorGeneración, Lector lector, GeneradorNgramProbabilidades generadorNgramProbabilidades, GeneradorVocabulario generadorVocabulario, Tokenizador tokenizador, Ngram ngram) {
        super(selectorGeneración, lector, generadorNgramProbabilidades, generadorVocabulario, tokenizador, ngram);
    }

    public void generadorTextoD() {
        if (recibirEntradaGeneracion() == 1) {
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();
                while (entradaTexto.size() != ngram.getTamañoNgram() - 1) {
                    entradaTexto.add(0, "<BOS>");
                }

                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) {

                    Map<String, Double> mapaPalabra = generadorNgramProbabilidades.buscarTokensSiguientes(entradaTexto);
                    String maxPalProb = mapaPalabra.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
                    if (maxPalProb != null) {
                        entradaTexto.add(maxPalProb);
                    } else {
                        entradaTexto = generarNumRandParaPalSiguiente(entradaTexto);
                    }
                    entradaTexto.remove(0);
                    System.out.println(entradaTexto);
                    maximoPalabras--;
                }
                cantOraciones--;
            } //AGREGAR A QUE SI LLEGA A <EOS> TERMINE.

        }

    }
}
