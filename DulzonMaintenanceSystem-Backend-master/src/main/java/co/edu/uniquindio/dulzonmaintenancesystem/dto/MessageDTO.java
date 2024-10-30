package co.edu.uniquindio.dulzonmaintenancesystem.dto;

public record MessageDTO<T>(
        boolean error,
        T respuesta
) {
}
