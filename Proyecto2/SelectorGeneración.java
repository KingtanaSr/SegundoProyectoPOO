package Proyecto2;

public class SelectorGeneración {
    private int opcionGeneracion;

    private int opcionCantOraciones;

    private int opcionTamMaximo;

    private final Lector lector;

    public SelectorGeneración(Lector lector) {
        this.lector = lector;
        this.opcionGeneracion = obtenerOpcionGeneracion();
        this.opcionCantOraciones = obtenerOpcionCantOraciones();
        this.opcionTamMaximo = obtenerTamMaximoSecuencia();
    }

    private int obtenerOpcionGeneracion() {
        System.out.println("Ingrese un 1 si desea que la generación sea determinística y un 2 si desea que sea no determinística");
        opcionGeneracion = lector.leerEntero();
        if(opcionGeneracion!=1 && opcionGeneracion!=2){
            System.out.println("El número que ingresaste no es una de las opciones válidas");
            return 0;
        }
        return opcionGeneracion;
    }

    private int obtenerOpcionCantOraciones(){
        System.out.println("Ingrese la cantidad de secuencias a generar. El valor debe estar entre 1 y 10.");
        opcionCantOraciones = lector.leerEntero();
        if(opcionCantOraciones < 1 || opcionCantOraciones > 10){
            System.out.println("El número que ingresaste no es una de las opciones válidas");
            return 0;
        }
        return opcionCantOraciones;
    }


    private int obtenerTamMaximoSecuencia(){
        System.out.println("Ingrese el tamano maximo de las secuencias a generar: ");
        opcionTamMaximo = lector.leerEntero();
        if(opcionTamMaximo > 1000){
            System.out.println("El tamano máximo de las secuencias que ingresaste supera el límite establecido");
            return 0;
        }
        return opcionTamMaximo;
    }

    public int getOpcionGeneracion() {
        return opcionGeneracion;
    }

    public int getOpcionCantOraciones(){
        return opcionCantOraciones;
    }

    public int getOpcionTamMaximo(){
        return opcionTamMaximo;
    }

}
