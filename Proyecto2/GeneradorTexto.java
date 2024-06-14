package Proyecto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class GeneradorTexto {
    private final LectorDeEntrada lectorDeEntrada;
    private final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    private final GeneradorVocabulario generadorVocabulario;
    private final Tokenizador tokenizador;
    private final Ngram ngram;
    private final SelectorGeneración selectorGeneración;
    private final ManipuladorTexto manipuladorTexto;
    private final SelectorPalabraSiguiente selectorPalabraSiguiente;


    public GeneradorTexto(SelectorGeneración selectorGeneración, LectorDeEntrada lectorDeEntrada,GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario,Tokenizador tokenizador, Ngram ngram, ManipuladorTexto manipuladorTexto, SelectorPalabraSiguiente selectorPalabraSiguiente){
        this.lectorDeEntrada = lectorDeEntrada;
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
        this.tokenizador = tokenizador;
        this.ngram = ngram;
        this.selectorGeneración = selectorGeneración;
        this.manipuladorTexto = manipuladorTexto;
        this.selectorPalabraSiguiente = selectorPalabraSiguiente;
    }
    private int recibirEntradaGeneracion(){
        return selectorGeneración.getOpcionGeneracion();
    }

    private int recibirEntradaMaximoPalabras(){
        return selectorGeneración.getOpcionTamMaximo();
    }

    private int recibirEntradaCantOraciones(){
        return selectorGeneración.getOpcionCantOraciones();
    }

    public List<String> recibirEntradasTexto(){
        System.out.print("Ingrese el texto de inicio: ");
        String input = lectorDeEntrada.leerCadena();
        List<String> lista = tokenizador.guardarTokens(input);
        return lista;
    }

    public List<String> generarTexto(){
        List<String> guardaTextos = new ArrayList<>(); // lista para guardar las secuencias creadas
        String maxPalProb;
            int cantOraciones = recibirEntradaCantOraciones();
            while (cantOraciones > 0) {
                List<String> entradaTexto = recibirEntradasTexto();
                manipuladorTexto.agregarBOS(entradaTexto, ngram.getTamañoNgram()-1);
                int maximoPalabras = recibirEntradaMaximoPalabras();
                while (maximoPalabras > 0) {
                    if (recibirEntradaGeneracion() == 1) {
                        Map<String, Double> mapaPalabra = generadorNgramProbabilidades.buscarTokensSiguientes(entradaTexto);
                        maxPalProb = mapaPalabra.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null); // String para la palabra más probable
                    } else {
                        maxPalProb = selectorPalabraSiguiente.seleccionarPalProb(generadorNgramProbabilidades, entradaTexto); // se selecciona la palabra siguiente segun las probabilidades
                    }
                    if (maxPalProb != null) {
                        entradaTexto.add(maxPalProb); // agrega al texto que se está generando la palabra que más aparece
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        if (maxPalProb.equals("<EOS>")) { // si se llega a que la palabra más probable es "<EOS>" se termina
                            break;
                        }
                        entradaTexto.remove(0); // se recorta
                    } else {
                        entradaTexto = selectorPalabraSiguiente.generarNumRandParaPalSiguiente(entradaTexto); // por si se ingresa una secuencia que no existe en el texto
                        guardaTextos.add(entradaTexto.get(ngram.getTamañoNgram() - 1)); // se obtiene el contenido de entradaTexto y se almacena en guardaTextos
                        entradaTexto.remove(0); // se recorta
                    }
                    maximoPalabras--;
                }
                cantOraciones--;
                manipuladorTexto.imprimirTexto(guardaTextos);
                System.out.println("");
                guardaTextos.clear();
        }
        return guardaTextos;
    }
}

