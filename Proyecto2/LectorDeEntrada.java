package Proyecto2;

import java.util.Scanner;

public class LectorDeEntrada {
    private Scanner scanner;
    public LectorDeEntrada() {
        this.scanner = new Scanner(System.in);
    }
    //método para leer un número entero
    public int leerEntero() {
        while (true) {
            String input = scanner.nextLine(); //se guarda la entrada en un String
            try {
                return Integer.parseInt(input);  // se convierte en entero
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número entero válido."); // por si no es un número válido
            }
        }
    }
    //método para leer un String
    public String leerCadena() {
        return scanner.nextLine();
    }
}