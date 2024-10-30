package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DtoEditarCuenta(
        @NotBlank(message = "El teléfono no puede estar vacío")
        @Pattern(
                regexp = "\\d{7,15}",
                message = "El teléfono debe contener entre 7 y 15 dígitos"
        )
        String telefono,

        @NotBlank(message = "La dirección no puede estar vacía")
        @Size(max = 100, message = "La dirección no puede exceder los 100 caracteres")
        String direccion,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).+$",
                message = "La contraseña debe contener al menos una letra mayúscula, una letra minúscula, un dígito y un carácter especial"
        )
        String password
) {

}
