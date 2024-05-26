package Proyecto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GeneradorNGram {
    private final Tokenizador tokenizador;

    public GeneradorNGram(Tokenizador tokenizador) {
        this.tokenizador = tokenizador;
    }
    public List<List<String>> generarNGram() {
        int n = obtenerN();

        List<String> tokens = tokenizador.getListaTokens();

        for(int i=0; i < n-1; i++){
            tokens.add("<EOS>");
            tokens.addFirst("<BOS>");
        }

        List<List<String>> nGrams = new ArrayList<>();

        if (tokens.size() >= n) {
            for (int i = 0; i <= tokens.size() - n; i++) {
                List<String> nGram = new ArrayList<>();
                for (int j = i; j < i + n; j++) {
                    nGram.add(tokens.get(j));
                }
                nGrams.add(nGram);
            }
        } else {
            System.out.println("La longitud de la lista de tokens es menor que n.");
        }

        return nGrams;
    }
    private static int obtenerN() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un número entero: ");
        System.out.println("antes");
        int numero = scanner.nextInt(); // ARREGLAR, NO SIRVE SCANNER
        return numero;

    }
}
