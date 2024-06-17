package Proyecto2;

import java.util.*;

public class GeneradorNgramProbabilidades {
    private final Tokenizador tokenizador;
    private final Ngram ngram;
    private final LectorDeArchivo lectorDeArchivo;

    public GeneradorNgramProbabilidades(Tokenizador tokenizador, Ngram ngram, LectorDeArchivo lectorDeArchivo) {
        this.lectorDeArchivo = lectorDeArchivo;
        this.tokenizador = tokenizador;
        this.ngram = ngram;
    }

    public Map<List<String>, Map<String, Double>> generarNGramProbabilidades() {
        int tamañoNgram = ngram.getTamañoNgram(); // se obtiene el tamaño del n-gram que se desea usar.
        List<String> tokens = tokenizador.guardarTokens(lectorDeArchivo.getContenidoArchivo()); // convierte el contenido del archivo en una lista de tokens

        Map<List<String>, Map<String, Integer>> nGramFrecuencias = new LinkedHashMap<>(); // almacena la frecuencia de aparición de cada n-gram junto con la frecuencia de aparición del siguiente token
        LinkedList<String> colaNgram = new LinkedList<>(); // utiliza una cola para construir los n-grams dinámicamente a medida que se procesa la lista de tokens.
        // recorre los tokens y construye n-grams
        // cuando hay suficiente cantidad de tokens para formar un n-gram, se agrega el n-gram actual y su siguiente token al mapa de frecuencias
        for (int i = 0; i < tokens.size(); i++) {
            if (i >= tamañoNgram - 1) {
                List<String> palabra = new ArrayList<>(colaNgram);
                String nextToken = tokens.get(i);

                nGramFrecuencias.putIfAbsent(palabra, new LinkedHashMap<>());
                Map<String, Integer> siguienteToken = nGramFrecuencias.get(palabra);
                siguienteToken.put(nextToken, siguienteToken.getOrDefault(nextToken, 0) + 1);

                colaNgram.poll(); // elimina el primer elemento de la cola
            }
            colaNgram.offer(tokens.get(i)); //añade el token actual al final de la cola.
        }

        // recorre el mapa de frecuencias y calcula la probabilidad de aparición de cada token dado un n-gram
        Map<List<String>, Map<String, Double>> nGramProbabilidades = new LinkedHashMap<>();
        for (Map.Entry<List<String>, Map<String, Integer>> valor : nGramFrecuencias.entrySet()) {
            List<String> nGramPalabra = valor.getKey();
            Map<String, Integer> tokenFrecuencias = valor.getValue();
            int totalFrecuencias = tokenFrecuencias.values().stream().mapToInt(Integer::intValue).sum();

            Map<String, Double> tokenProbabilidades = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> token : tokenFrecuencias.entrySet()) {
                tokenProbabilidades.put(token.getKey(), (double) token.getValue() / totalFrecuencias);
            }
            nGramProbabilidades.put(nGramPalabra, tokenProbabilidades);
        }
        return nGramProbabilidades; // devuelve el mapa que asocia a cada n-gram con un mapa de probabilidades para los tokens que le siguen.
    }

    public Map<String, Double> buscarTokensSiguientes(List<String> nGramKey) {
        Map<List<String>, Map<String, Double>> nGramProbabilidades = generarNGramProbabilidades();
        Map<String, Double> tokenProbabilidades = nGramProbabilidades.getOrDefault(nGramKey, Collections.emptyMap());
        return tokenProbabilidades;
    }


}
