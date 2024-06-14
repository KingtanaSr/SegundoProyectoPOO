package Proyecto2;

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

        ManipuladorTexto mT = new ManipuladorTexto();

        SelectorPalabraSiguiente SP = new SelectorPalabraSiguiente(nGP,gn);

        GeneradorTexto GT = new GeneradorTexto(gdT,lectorr,nGP,gn,tokenizador,ngram,mT, SP);
        GT.generarTexto();

    }
}
