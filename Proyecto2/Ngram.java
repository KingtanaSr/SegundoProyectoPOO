package Proyecto2;

public class Ngram {

    private int tam; // atributo para el tamaño del ngram
    private final LectorDeEntrada lectorDeEntrada; // atributo para el lector de la entrada del tamaño del ngram

    public Ngram(LectorDeEntrada lectorDeEntrada) throws DimensionInvalida {
        this.lectorDeEntrada = lectorDeEntrada;
        this.tam = obtenerTamañoNgram();
    }

    // método para obtener del usuario la entrada que indica el tamaño del ngram
    private int obtenerTamañoNgram() throws DimensionInvalida{
        System.out.print("Ingrese el tamaño del n-gram, el número debe estar entre 2 y 10: ");
        tam = lectorDeEntrada.leerEntero(); // se lee la entrada mediante el metodo leerEntero
        if (tam < 2 || tam > 10) {
            throw new DimensionInvalida("El tamaño del n-gram que deseas realizar es inválido.");//por si el tamaño esta fuera del rango valido
        }
        return tam;
    }

    public int getTamañoNgram() {
        return tam;
    }
}