package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoLogin(
        @NotNull(message = "El correo electrónico no puede estar vacío.")
        @Email(message = "El correo electrónico debe tener un formato válido.")
        String email,

        @NotNull(message = "La contraseña no puede estar vacía.")
        @NotBlank(message = "La contraseña no puede estar vacía.")
        String password
) {
}
