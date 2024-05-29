package Proyecto2;

public class SelectorGeneración {
    private int opcionGeneracion;

    private int opcionCantOraciones;

    private final Lector lector;

    public SelectorGeneración(Lector lector) {
        this.lector = lector;
        this.opcionGeneracion = obtenerOpcionGeneracion();
        this.opcionCantOraciones = obtenerOpcionCantOraciones();
    }

    private int obtenerOpcionGeneracion() {
        System.out.println("Ingrese un 1 si desea que la generación sea determinística y un 2 si desea que sea no determinística");
        opcionGeneracion = lector.leerEntero();
        if(opcionGeneracion!=1 && opcionCantOraciones!=2){
            System.out.println("El número que ingresaste no es una de las opciones válidas");
            return 0;
        }
        return opcionGeneracion;
    }

    private int obtenerOpcionCantOraciones(){
        System.out.println("Ingrese la cantidad de secuencias a generar. El valor debe estar entre 1 y 10.");
        opcionCantOraciones = lector.leerEntero();
        if(opcionCantOraciones < 1 || opcionCantOraciones < 10){
            System.out.println("El número que ingresaste no es una de las opciones válidas");
            return 0;
        }
        return opcionCantOraciones;
    }

    public int getOpcionGeneracion() {
        return opcionGeneracion;
    }

    public int getOpcionCantOraciones(){
        return opcionCantOraciones;
    }

}
