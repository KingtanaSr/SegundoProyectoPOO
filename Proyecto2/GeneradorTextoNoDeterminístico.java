package Proyecto2;

import java.util.*;

public class GeneradorTextoNoDeterminístico extends GeneradorTexto {
    public GeneradorTextoNoDeterminístico(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada, GeneradorNgramProbabilidades generadorNgramProbabilidades, GeneradorVocabulario generadorVocabulario, Tokenizador tokenizador, Ngram ngram) {
        super(selectorGeneración, lectorDeEntrada, generadorNgramProbabilidades, generadorVocabulario, tokenizador, ngram);
    }

    // método que implementa el generador de texto no deterministico
    public List<String> generadorTextoND() {
        List<String> guardaTextos = new ArrayList<>(); // lista para guardar los textos/palabras generadas
        if (recibirEntradaGeneracion() == 2) {
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();
                agregarBOS(entradaTexto);
                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) { // mientras no se haya alcanzado el maximo de palabras indicado se sigue generando texto
                    String palsiguiente = seleccionarPalProb(generadorNgramProbabilidades, entradaTexto); // se selecciona la palabra siguiente segun las probabilidades
                    if(palsiguiente!= null) {
                        entradaTexto.add(palsiguiente); // se agrega la palabra siguiente generada a entrada texto
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se guarda la palabra generada
                        if (palsiguiente.equals("<EOS>")) { // si se llega a generar un EOS entonces se termina la generacion
                            break;
                        }
                        entradaTexto.remove(0);
                    }else{ //
                        entradaTexto = generarNumRandParaPalSiguiente(entradaTexto);
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1));
                        entradaTexto.remove(0);
                    }
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
    public static String seleccionarPalProb(GeneradorNgramProbabilidades ngramProbabilidades, List<String> palabra) {
        Map<String, Double> probabilidades = ngramProbabilidades.buscarTokensSiguientes(palabra);
        if (probabilidades == null || probabilidades.isEmpty()) {
            return null;
        }
        double totalWeight = 0.0;
        for (double weight : probabilidades.values()) {
            totalWeight += weight;
        }

        double randomValue = new Random().nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;

        for (Map.Entry<String, Double> entry : probabilidades.entrySet()) {
            cumulativeWeight += entry.getValue();
            if (cumulativeWeight >= randomValue) {
                return entry.getKey();
            }
        }

        return null;
    }
}