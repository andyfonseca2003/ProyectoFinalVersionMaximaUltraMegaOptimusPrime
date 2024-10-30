package co.edu.uniquindio.dulzonmaintenancesystem.Exception.Cuenta;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
