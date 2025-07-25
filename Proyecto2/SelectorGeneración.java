package Proyecto2;

public class SelectorGeneración {
    private int opcionGeneracion;

    private int opcionCantOraciones;

    private int opcionTamMaximo;

    private final LectorDeEntrada lectorDeEntrada;

    public SelectorGeneración(LectorDeEntrada lectorDeEntrada) throws DimensionInvalida {
        this.lectorDeEntrada = lectorDeEntrada;
        this.opcionGeneracion = obtenerOpcionGeneracion();
        this.opcionCantOraciones = obtenerOpcionCantOraciones();
        this.opcionTamMaximo = obtenerTamMaximoSecuencia();
    }

    // método para obtener la opcion de generacion del usuario ya sea deterministica o no deterministica
    private int obtenerOpcionGeneracion() throws DimensionInvalida{
        System.out.print("Ingrese un 1 si desea que la generación sea determinística y un 2 si desea que sea no determinística: ");
        opcionGeneracion = lectorDeEntrada.leerEntero();
        if(opcionGeneracion != 1 && opcionGeneracion != 2){
            throw new DimensionInvalida("El número que ingresaste no es una de las opciones válidas");
        }
        return opcionGeneracion;
    }

    // método para obtener del usuario  la cantidad de oraciones a generar
    private int obtenerOpcionCantOraciones() throws DimensionInvalida {
        System.out.print("Ingrese la cantidad de secuencias a generar. El valor debe estar entre 1 y 10: ");
        opcionCantOraciones = lectorDeEntrada.leerEntero();
        if(opcionCantOraciones < 1 || opcionCantOraciones > 10){
            throw new DimensionInvalida("El número que ingresaste no es una de las opciones válidas");
        }
        return opcionCantOraciones;
    }


    // método para obtener del usuario  el tamaño máximo de cada secuencia/oraciones a generar
    private int obtenerTamMaximoSecuencia() throws DimensionInvalida {
        System.out.print("Ingrese el tamano maximo de las secuencias a generar: ");
        opcionTamMaximo = lectorDeEntrada.leerEntero();
        if(opcionTamMaximo > 1000){
            throw new DimensionInvalida("El tamano máximo de las secuencias que ingresaste supera el límite establecido");
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
