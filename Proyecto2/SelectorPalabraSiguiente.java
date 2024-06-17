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

    //método que genera un número aleatorio que representa el índice para escoger en la lista de vocabulario
    public List<String> generarNumRandParaPalSiguiente(List<String> inputUsuario){
        if (generadorNgramProbabilidades.buscarTokensSiguientes(inputUsuario).isEmpty()) {  // se verifica que la palabra no esté en el texto
            Random random = new Random();
            List<String> vocabulario = generadorVocabulario.generarVocabulario();
            int indiceAleatorio = random.nextInt(vocabulario.size());
            inputUsuario.add(vocabulario.get(indiceAleatorio)); // se escoge la palabra del vocabulario con el índice creado y se añade

        }
        return inputUsuario;
    }

    //método para seleccionar la palabra más probable en base a sus pesos, es implementada para la generación NO determinística
    public String seleccionarPalProb(GeneradorNgramProbabilidades ngramProbabilidades, List<String> palabra) {
        Map<String, Double> probabilidades = ngramProbabilidades.buscarTokensSiguientes(palabra); // se crea el mapa de las palabras con sus respectivas probabilidades
        if (probabilidades == null || probabilidades.isEmpty()) {
            return null;
        }
        double pesoTotal = 0.0; // se inicializa una variable que representa el peso total
        for (double peso : probabilidades.values()) {
            pesoTotal += peso; // se va recorriendo los pesos del mapa y se suma a la varible 'pesoTotal'
        }

        double valorAleatorio = new Random().nextDouble() * pesoTotal; // se multiplica por el 'pesoTotal' para que esté dentro del rango
        double pesoAcumulado = 0.0;

        for (Map.Entry<String, Double> valor : probabilidades.entrySet()) {
            pesoAcumulado += valor.getValue(); // se suma los pesos de cada palabra con su respectivo(s) peso(s)
            if (pesoAcumulado >= valorAleatorio) {
                return valor.getKey(); // si el pesoAcumulado es mayor al valorAleatorio, se retorna la palabra
            }
        }
        return null;
    }
}
