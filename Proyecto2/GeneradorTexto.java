package Proyecto2;

import java.util.List;
import java.util.Random;

public class GeneradorTexto {
    public final LectorDeEntrada lectorDeEntrada;
    public final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    public final GeneradorVocabulario generadorVocabulario;
    public final Tokenizador tokenizador;
    public final Ngram ngram;
    public final SelectorGeneración selectorGeneración;


    public GeneradorTexto(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada,GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario,Tokenizador tokenizador, Ngram ngram){
        this.lectorDeEntrada = lectorDeEntrada;
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
        this.tokenizador = tokenizador;
        this.ngram = ngram;
        this.selectorGeneración = selectorGeneración;
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

}
