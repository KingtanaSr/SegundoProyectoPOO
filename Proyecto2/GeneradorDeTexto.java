package Proyecto2;

import javax.swing.JOptionPane;

public class GeneradorDeTexto {

    private final Ngram ngram;
    private final GeneradorNgramProbabilidades generadorNgramProbabilidades;
    private int opcionGeneracion;

    public GeneradorDeTexto(GeneradorNgramProbabilidades generadorNgramProbabilidades, Ngram ngram, int opcionGeneracion) {
        this.generadorNgramProbabilidades = generadorNgramProbabilidades;
        this.ngram = ngram;
        this.opcionGeneracion = obtenerOpcion();
    }

    private int obtenerOpcion() {
        while (true) {
            String input = JOptionPane.showInputDialog(null, "Ingrese un 1 si desea que la generación sea determinística y un 2 si desea que sea no determinística");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada.");
                System.exit(0);
            }
            try {
                opcionGeneracion = Integer.parseInt(input);
                if (opcionGeneracion == 1 || opcionGeneracion == 2) {
                    return opcionGeneracion;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida (1 o 2).");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa un número entero válido.");
            }
        }
    }

    public int getOpcionGeneracion() {
        return opcionGeneracion;
    }

}
