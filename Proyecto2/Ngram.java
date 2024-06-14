package Proyecto2;

public class Ngram {

    private int tam; // atributo para el tamaño del ngram
    private final LectorDeEntrada lectorDeEntrada; // atributo para el lector de la entrada del tamaño del ngram

    public Ngram(LectorDeEntrada lectorDeEntrada) {
        this.lectorDeEntrada = lectorDeEntrada;
        this.tam = obtenerTamañoNgram();
    }

    // método para obtener del usuario la entrada que indica el tamaño del ngram
    private int obtenerTamañoNgram() {
        System.out.print("Ingrese el tamaño del n-gram, el número debe estar entre 2 y 10: ");
        tam = lectorDeEntrada.leerEntero(); // se lee la entrada mediante el metodo leerEntero

        if (tam < 2 || tam > 10) { //por si el tamaño esta fuera del rango valido
            System.out.println("El tamaño del n-gram que deseas realizar es inválido.");
            return 0;
        }

        return tam;
    }

    public int getTamañoNgram() {
        return tam;
    }
}