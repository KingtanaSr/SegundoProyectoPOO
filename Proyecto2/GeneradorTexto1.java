package Proyecto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class GeneradorTexto1 {
    public final LectorDeEntrada lectorDeEntrada;
    public final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    public final GeneradorVocabulario generadorVocabulario;
    public final Tokenizador tokenizador;
    public final Ngram ngram;
    public final SelectorGeneración selectorGeneración;

    public GeneradorTexto1(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada,GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario,Tokenizador tokenizador, Ngram ngram){
        this.lectorDeEntrada = lectorDeEntrada;
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
        this.tokenizador = tokenizador;
        this.ngram = ngram;
        this.selectorGeneración = selectorGeneración;
    }

    public int recibirEntradaGeneracion(){
        return selectorGeneración.getOpcionGeneracion();
    }

    public int recibirEntradaMaximoPalabras(){
        return selectorGeneración.getOpcionTamMaximo();
    }

    public int recibirEntradaCantOraciones(){
        return selectorGeneración.getOpcionCantOraciones();
    }


    public List<String> recibirEntradasTexto(){
        System.out.print("Ingrese el texto de inicio: ");
        String input = lectorDeEntrada.leerCadena();
        List<String> lista = tokenizador.guardarTokens(input);
        return lista;
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

    public void agregarBOS(List<String> lista){
        while (lista.size() != ngram.getTamañoNgram() - 1) {
            lista.add(0, "<BOS>");
        }
    }

    //método para imprimir las secuencias generadas
    public void imprimirTexto(List<String> listaTexto){
        for(String palabra : listaTexto){
            if (palabra.equals("<EOS>")){
                break;
            }
            System.out.print(palabra + " ");
            try {
                Thread.sleep(1000); // Pausa de 1 segundo (1000 milisegundos)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
        }
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

    public List<String> generadorTextoGeneral(){
        List<String> guardaTextos = new ArrayList<>(); // lista para guardar las secuencias creadas
        String maxPalProb = "";
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();
                agregarBOS(entradaTexto);
                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) {
                    if (recibirEntradaGeneracion() == 1) {
                        Map<String, Double> mapaPalabra = generadorNgramProbabilidades.buscarTokensSiguientes(entradaTexto);
                        maxPalProb = mapaPalabra.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null); // String para la palabra más probable
                    } else {
                        maxPalProb = seleccionarPalProb(generadorNgramProbabilidades, entradaTexto); // se selecciona la palabra siguiente segun las probabilidades
                    }
                    if (maxPalProb != null) {
                        entradaTexto.add(maxPalProb); // agrega al texto que se está generando la palabra que más aparece
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        if (maxPalProb.equals("<EOS>")) { // si se llega a que la palabra más probable es "<EOS>" se termina
                            break;
                        }
                        entradaTexto.remove(0); // se recorta
                    } else {
                        entradaTexto = generarNumRandParaPalSiguiente(entradaTexto); // por si se ingresa una secuencia que no existe en el texto
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        entradaTexto.remove(0); // se recorta
                    }
                    maximoPalabras--;
                }
                cantOraciones--;
                imprimirTexto(guardaTextos);
                System.out.println("");
                guardaTextos.clear();
        }
        return guardaTextos;
    }
}

