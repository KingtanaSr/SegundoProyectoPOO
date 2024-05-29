package Proyecto2;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Lector lectorr = new Lector();

        LectorDeArchivo lector = new LectorDeArchivo(lectorr);

        Tokenizador tokenizador = new Tokenizador(lector);

        Ngram ngram = new Ngram(lectorr);

        tokenizador.guardarTokens();

        List<String> tokens = tokenizador.getListaTokens();
        //System.out.println(tokens);

       GeneradorVocabulario gn = new GeneradorVocabulario(tokenizador);

        List<String> vocabulario = gn.generarVocabulario();
        //System.out.println(vocabulario);

        GeneradorNGram nG = new GeneradorNGram(tokenizador, ngram);
        System.out.println(nG.generarNGram());

        GeneradorNgramProbabilidades nGP = new GeneradorNgramProbabilidades(tokenizador,ngram);
        System.out.println(nGP.generarNGramProbabilidades());


        SelecciónGeneración gdT = new SelecciónGeneración(lectorr);
        //System.out.println(gdT.getOpcionGeneracion());

    }
}

