package Proyecto2;

public class GPTec {

    public static void main(String[] args) throws DimensionInvalida {

        LectorDeEntrada lectorDeEntrada = new LectorDeEntrada();

        LectorDeArchivo lectorDeArchivo = new LectorDeArchivo(lectorDeEntrada);

        Ngram ngram = new Ngram(lectorDeEntrada);

        lectorDeArchivo.modificarContenido(ngram.getTamañoNgram()-1);

        Tokenizador tokenizador = new Tokenizador(lectorDeArchivo);

        GeneradorVocabulario generadorVocabulario = new GeneradorVocabulario(tokenizador, lectorDeArchivo);

        GeneradorNgramProbabilidades generadorNgramProbabilidades = new GeneradorNgramProbabilidades(tokenizador,ngram, lectorDeArchivo);

        SelectorGeneración selectorGeneración = new SelectorGeneración(lectorDeEntrada);

        ManipuladorTexto manipuladorTexto = new ManipuladorTexto();

        SelectorPalabraSiguiente selectorPalabraSiguiente = new SelectorPalabraSiguiente(generadorNgramProbabilidades, generadorVocabulario);

        GeneradorTexto generadorTexto = new GeneradorTexto(selectorGeneración,lectorDeEntrada, generadorNgramProbabilidades,tokenizador,ngram, manipuladorTexto, selectorPalabraSiguiente);
        generadorTexto.generarTexto();

    }
}
