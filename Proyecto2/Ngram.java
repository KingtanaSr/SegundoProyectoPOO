package Proyecto2;

public class Ngram {
    private int tam;
    private final LectorDeEntrada lectorDeEntrada;

    public Ngram(LectorDeEntrada lectorDeEntrada) {
        this.lectorDeEntrada = lectorDeEntrada;
        this.tam = obtenerTamañoNgram();
    }

    private int obtenerTamañoNgram() {
        System.out.print("Ingrese el tamaño del n-gram, el número debe estar entre 2 y 10: ");
        tam = lectorDeEntrada.leerEntero();

        if (tam < 2 || tam > 10) {
            System.out.println("El tamaño del n-gram que deseas realizar es inválido.");
            return 0;
        }

        return tam;
    }

    public int getTamañoNgram() {
        return tam;
    }
}