package Proyecto2;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SelectorPalabraSiguiente {
    private final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    private final GeneradorVocabulario generadorVocabulario;

    public SelectorPalabraSiguiente(GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario){
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
    }
    public List<String> generarNumRandParaPalSiguiente(List<String> inputUsuario){
        if (generadorNgramProbabilidades.buscarTokensSiguientes(inputUsuario).isEmpty()) {
            Random random = new Random();
            List<String> vocabulario = generadorVocabulario.generarVocabulario();
            int indiceAleatorio = random.nextInt(vocabulario.size());
            inputUsuario.add(vocabulario.get(indiceAleatorio));

        }
        return inputUsuario;
    }


    public  String seleccionarPalProb(GeneradorNgramProbabilidades ngramProbabilidades, List<String> palabra) {
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
