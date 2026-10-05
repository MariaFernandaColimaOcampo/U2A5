package mx.edu.utez.tarea04102026.exception.customExceptions;

public class CustomBadRequestExceptions extends RuntimeException {

    public CustomBadRequestExceptions(String mensaje) {
        super(mensaje);
    }
}