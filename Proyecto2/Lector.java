package Proyecto2;

import java.util.Scanner;

public class Lector {
    private Scanner scanner;
    public Lector() {
        this.scanner = new Scanner(System.in);
    }
    public int leerEntero() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número entero válido.");
            }
        }
    }
    public String leerCadena() {
        return scanner.nextLine();
    }
}