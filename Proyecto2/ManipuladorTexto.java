package Proyecto2;

import java.util.List;

public class ManipuladorTexto {
    public void agregarBOS(List<String> lista, int tam){
        while (lista.size() != tam) {
            lista.addFirst( "<BOS>");
        }
    }

    //método para imprimir las secuencias generadas
    public void imprimirTexto(List<String> listaTexto){
        for(String palabra : listaTexto){
            if (palabra.equals("<EOS>")){
                break;
            }
            System.out.print(palabra + " ");
            try {
                Thread.sleep(1000); // Pausa de 1 segundo (1000 milisegundos)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("El hilo fue interrumpido: " + e.getMessage());
            }
        }
    }
}
