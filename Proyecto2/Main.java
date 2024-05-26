package Proyecto2;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        LectorDeArchivo lector = new LectorDeArchivo();

        Tokenizador tokenizador = new Tokenizador(lector);

        Ngram ngram = new Ngram();

        tokenizador.guardarTokens();

        List<String> tokens = tokenizador.getListaTokens();
        //System.out.println(tokens);

        GeneradorVocabulario gn = new GeneradorVocabulario(tokenizador);

        List<String> vocabulario = gn.generarVocabulario();
        //System.out.println(vocabulario);

        GeneradorNGram nG = new GeneradorNGram(tokenizador, ngram);
        GeneradorNgramProbabilidades nGP = new GeneradorNgramProbabilidades(tokenizador,ngram);
        System.out.println(nGP.generarNGramProbabilidades());

    }
}

