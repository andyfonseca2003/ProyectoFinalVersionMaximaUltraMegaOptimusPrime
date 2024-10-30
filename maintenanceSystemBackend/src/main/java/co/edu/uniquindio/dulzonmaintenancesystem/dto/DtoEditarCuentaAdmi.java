package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DtoEditarCuentaAdmi(
        @NotNull(message = "El estado de la cuenta no puede ser nulo")
        EstadoCuenta estadoCuenta,

        @NotNull(message = "El rol no puede ser nulo")
        Rol rol,

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
        String nombre,

        @NotBlank(message = "El apellido no puede estar vacío")
        @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
        String apellido
) {
}
