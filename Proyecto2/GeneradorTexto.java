package Proyecto2;

import java.util.List;
import java.util.Random;

public class GeneradorTexto {
    public final Lector lector;
    public final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    public final GeneradorVocabulario generadorVocabulario;
    public final Tokenizador tokenizador;
    public final Ngram ngram;
    public final SelectorGeneración selectorGeneración;


    public GeneradorTexto(SelectorGeneración selectorGeneración, Lector lector,GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario,Tokenizador tokenizador, Ngram ngram){
        this.lector = lector;
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
        this.tokenizador = tokenizador;
        this.ngram = ngram;
        this.selectorGeneración = selectorGeneración;
    }


    public void imprimirTexto(){

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
        String input = lector.leerCadena();
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
}
