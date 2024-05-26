package Proyecto2;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        LectorDeArchivo lector = new LectorDeArchivo();

        Tokenizador tokenizador = new Tokenizador(lector);

        tokenizador.guardarTokens();

        List<String> tokens = tokenizador.getListaTokens();
        //System.out.println(tokens);

        GeneradorVocabulario gn = new GeneradorVocabulario(tokenizador);

        List<String> vocabulario = gn.generarVocabulario();
        //System.out.println(vocabulario);

        GeneradorNGram nG = new GeneradorNGram(tokenizador);
        List<List<String>> nGram= nG.generarNGram();


    }
}

