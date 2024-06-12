package Proyecto2;

import java.util.*;

public class GeneradorTextoDeterminístico extends GeneradorTexto{
    public GeneradorTextoDeterminístico(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada, GeneradorNgramProbabilidades generadorNgramProbabilidades, GeneradorVocabulario generadorVocabulario, Tokenizador tokenizador, Ngram ngram) {
        super(selectorGeneración, lectorDeEntrada, generadorNgramProbabilidades, generadorVocabulario, tokenizador, ngram);
    }

    public List<String> generadorTextoD() {
        List<String> guardaTextos = new ArrayList<>();
        if (recibirEntradaGeneracion() == 1) {
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();

                while (entradaTexto.size() != ngram.getTamañoNgram() - 1) {
                    entradaTexto.add(0, "<BOS>");
                }
                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) {
                 // if (recibirEntradaGeneracion() == 1){
                    Map<String, Double> mapaPalabra = generadorNgramProbabilidades.buscarTokensSiguientes(entradaTexto);
                    String maxPalProb = mapaPalabra.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
                    if(maxPalProb != null) {
                        entradaTexto.add(maxPalProb);
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1));
                        if(maxPalProb.equals("<EOS>")){
                            break;
                        }
                        entradaTexto.remove(0);
                    }else {
                        entradaTexto = generarNumRandParaPalSiguiente(entradaTexto);
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1));
                        entradaTexto.remove(0);
                    }
                    //} else{
                    // }
                    maximoPalabras--;

                }
                cantOraciones--;
                imprimirTexto(guardaTextos);
                System.out.println("");
                guardaTextos.clear();
                
            }

        }
        return guardaTextos;
    }
}
