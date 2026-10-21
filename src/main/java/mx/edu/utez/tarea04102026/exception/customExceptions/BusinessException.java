package mx.edu.utez.tarea04102026.exception.customExceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}