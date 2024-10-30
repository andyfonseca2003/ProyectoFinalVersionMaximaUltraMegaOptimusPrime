package co.edu.uniquindio.dulzonmaintenancesystem.dto;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
