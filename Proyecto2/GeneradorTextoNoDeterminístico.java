package Proyecto2;
import java.util.List;
public class GeneradorTextoNoDeterminístico {
    private Lector lector;
    private GeneradorNgramProbabilidades generadorNgramProbabilidades;
    private GeneradorVocabulario generadorVocabulario;
    private Tokenizador tokenizador;
    public GeneradorTextoNoDeterminístico(Lector lector,GeneradorNgramProbabilidades generadorNgramProbabilidades,GeneradorVocabulario generadorVocabulario,Tokenizador tokenizador){
        this.lector = lector;
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.generadorVocabulario = generadorVocabulario;
        this.tokenizador = tokenizador;
    }
    public List<String> generadorTextoND(){
        System.out.print("Ingrese el texto de inicio: ");
        String input= lector.leerCadena();
        List<String> lista = tokenizador.guardarTokens(input);
        return lista;
    }
}
