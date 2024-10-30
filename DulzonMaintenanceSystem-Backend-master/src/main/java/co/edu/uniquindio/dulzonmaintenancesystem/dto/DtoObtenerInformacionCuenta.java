package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record DtoObtenerInformacionCuenta(
        String cedula,
        String nombre,
        String Apellido,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento,
        @Email(message = "El correo debe tener un formato válido.")
        @NotBlank(message = "El email no puede estar vacío.")
        String email
) {
}
