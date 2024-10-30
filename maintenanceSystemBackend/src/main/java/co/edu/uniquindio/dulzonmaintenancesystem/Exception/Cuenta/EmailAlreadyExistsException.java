package co.edu.uniquindio.dulzonmaintenancesystem.Exception.Cuenta;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
