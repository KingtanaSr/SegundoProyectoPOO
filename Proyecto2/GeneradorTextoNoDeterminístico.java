package Proyecto2;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class GeneradorTextoNoDeterminístico extends GeneradorTexto {
    public GeneradorTextoNoDeterminístico(SelectorGeneración selectorGeneración, Lector lector, GeneradorNgramProbabilidades generadorNgramProbabilidades, GeneradorVocabulario generadorVocabulario, Tokenizador tokenizador, Ngram ngram) {
        super(selectorGeneración, lector, generadorNgramProbabilidades, generadorVocabulario, tokenizador, ngram);
    }

    public List<String> generadorTextoND() {
       List<String> lista = recibirEntradasTexto();
            while (selectorGeneración.getOpcionGeneracion() == 2) {
                while (lista.size() != ngram.getTamañoNgram() - 1) {
                    lista.addFirst("<BOS>");
                }
                /*if (generadorNgramProbabilidades.buscarTokensSiguientes(lista).isEmpty()) {
                    Random random = new Random();
                    List<String> vocabulario = generadorVocabulario.generarVocabulario();
                    int indiceAleatorio = random.nextInt(vocabulario.size());
                    lista.add(vocabulario.get(indiceAleatorio));
                }*/
                System.out.println(generadorNgramProbabilidades.buscarTokensSiguientes(lista));
            }
        return lista;
    }

}

