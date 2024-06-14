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
        int n = ngram.getTamañoNgram();
        List<String> tokens = new LinkedList<>(tokenizador.guardarTokens(lectorDeArchivo.getContenidoArchivo()));

        Map<List<String>, Map<String, Integer>> nGramFrecuencias = new LinkedHashMap<>();

        LinkedList<String> nGramQueue = new LinkedList<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (i >= n - 1) {
                List<String> nGramKey = new ArrayList<>(nGramQueue);
                String nextToken = tokens.get(i);

                nGramFrecuencias.putIfAbsent(nGramKey, new LinkedHashMap<>());
                Map<String, Integer> nextTokenMap = nGramFrecuencias.get(nGramKey);
                nextTokenMap.put(nextToken, nextTokenMap.getOrDefault(nextToken, 0) + 1);

                nGramQueue.poll();
            }
            nGramQueue.offer(tokens.get(i));
        }

        Map<List<String>, Map<String, Double>> nGramProbabilidades = new LinkedHashMap<>();
        for (Map.Entry<List<String>, Map<String, Integer>> entry : nGramFrecuencias.entrySet()) {
            List<String> nGramKey = entry.getKey();
            Map<String, Integer> tokenFrecuencias = entry.getValue();
            int totalFrecuencias = tokenFrecuencias.values().stream().mapToInt(Integer::intValue).sum();

            Map<String, Double> tokenProbabilidades = new LinkedHashMap<>();
            for (Map.Entry<String, Integer> tokenEntry : tokenFrecuencias.entrySet()) {
                tokenProbabilidades.put(tokenEntry.getKey(), (double) tokenEntry.getValue() / totalFrecuencias);
            }
            nGramProbabilidades.put(nGramKey, tokenProbabilidades);
        }
        return nGramProbabilidades;
    }

    public Map<String, Double> buscarTokensSiguientes(List<String> nGramKey) {
        Map<List<String>, Map<String, Double>> nGramProbabilidades = generarNGramProbabilidades();
        Map<String, Double> tokenProbabilidades = nGramProbabilidades.getOrDefault(nGramKey, Collections.emptyMap());
        return tokenProbabilidades;
    }


}
