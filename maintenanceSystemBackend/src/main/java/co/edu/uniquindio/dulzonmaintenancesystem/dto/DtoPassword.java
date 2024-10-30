package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import jakarta.validation.constraints.NotBlank;

public record DtoPassword(
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
) {
}
