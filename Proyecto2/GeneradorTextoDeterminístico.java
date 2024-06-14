package Proyecto2;

import java.util.*;

public class GeneradorTextoDeterminístico extends GeneradorTexto{
    public GeneradorTextoDeterminístico(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada, GeneradorNgramProbabilidades generadorNgramProbabilidades, GeneradorVocabulario generadorVocabulario, Tokenizador tokenizador, Ngram ngram) {
        super(selectorGeneración, lectorDeEntrada, generadorNgramProbabilidades, generadorVocabulario, tokenizador, ngram);
    }

    public List<String> generadorTextoD() {
        List<String> guardaTextos = new ArrayList<>(); // lista para guardar las secuencias creadas
        if (recibirEntradaGeneracion() == 1) {
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();
                agregarBOS(entradaTexto);
                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) {
                    Map<String, Double> mapaPalabra = generadorNgramProbabilidades.buscarTokensSiguientes(entradaTexto);
                    String maxPalProb = mapaPalabra.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null); // String para la palabra más probable
                    if(maxPalProb != null) {
                        entradaTexto.add(maxPalProb); // agrega al texto que se está generando la palabra que más aparece
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        if(maxPalProb.equals("<EOS>")){ // si se llega a que la palabra más probable es "<EOS>" se termina
                            break;
                        }
                        entradaTexto.remove(0); // se recorta
                    }else {
                        entradaTexto = generarNumRandParaPalSiguiente(entradaTexto); // por si se ingresa una secuencia que no existe en el texto
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        entradaTexto.remove(0); // se recorta
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
