package Proyecto2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPTec {

    public static void main(String[] args) {
        LectorDeEntrada lectorr = new LectorDeEntrada();

        LectorDeArchivo lector = new LectorDeArchivo(lectorr);

        Ngram ngram = new Ngram(lectorr);

        lector.modificarContenido(ngram.getTamañoNgram()-1);

        Tokenizador tokenizador = new Tokenizador(lector);

        GeneradorVocabulario gn = new GeneradorVocabulario(tokenizador, lector);

        GeneradorNgramProbabilidades nGP = new GeneradorNgramProbabilidades(tokenizador,ngram,lector);

        SelectorGeneración gdT = new SelectorGeneración(lectorr);

        //GeneradorTextoNoDeterminístico GTND = new GeneradorTextoNoDeterminístico(gdT,lectorr,nGP,gn,tokenizador,ngram);
        //GTND.generadorTextoND();

        //GeneradorTextoDeterminístico GTD = new GeneradorTextoDeterminístico(gdT,lectorr,nGP,gn,tokenizador,ngram);
        //GTD.generadorTextoD();

        GeneradorTexto1 GT = new GeneradorTexto1(gdT,lectorr,nGP,gn,tokenizador,ngram);
        GT.generadorTextoGeneral();

    }
}
