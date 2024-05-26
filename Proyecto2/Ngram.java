package Proyecto2;

import javax.swing.*;


public class Ngram {
    private int tam;
    public Ngram(){
        this.tam = obtenerTamañoNgram();
    }
    private int obtenerTamañoNgram() {
        String input = JOptionPane.showInputDialog(null, "Ingresa un número entero:");
        try {
            tam = Integer.parseInt(input);
            return tam;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingresa un número entero válido.");
            return 0;
        }
    }

    public int getTamañoNgram() {
        return tam;
    }
}
