package Proyecto2;
import java.util.Arrays;
import java.util.List;

public class GPTec {

    public static void main(String[] args) {
        Lector lectorr = new Lector();

        LectorDeArchivo lector = new LectorDeArchivo(lectorr);

        Tokenizador tokenizador = new Tokenizador(lector);

        Ngram ngram = new Ngram(lectorr);

        List<String> tokens = tokenizador.guardarTokens(lector.getContenidoArchivo());
        //System.out.println(tokens);

        GeneradorVocabulario gn = new GeneradorVocabulario(tokenizador, lector);

        List<String> vocabulario = gn.generarVocabulario();
        //System.out.println(vocabulario);

        //GeneradorNGram nG = new GeneradorNGram(tokenizador, ngram,lector);
        //System.out.println(nG.generarNGram());

        GeneradorNgramProbabilidades nGP = new GeneradorNgramProbabilidades(tokenizador,ngram,lector);
        //System.out.println(nGP.generarNGramProbabilidades());


        SelectorGeneración gdT = new SelectorGeneración(lectorr);
        //System.out.println(gdT.getOpcionGeneracion());

        GeneradorTextoNoDeterminístico GTND = new GeneradorTextoNoDeterminístico(gdT,lectorr,nGP,gn,tokenizador,ngram);
        //System.out.println(GTND.generadorTextoND());

        GeneradorTextoDeterminístico GTD = new GeneradorTextoDeterminístico(gdT,lectorr,nGP,gn,tokenizador,ngram);
        GTD.generadorTextoD();

    }
}
