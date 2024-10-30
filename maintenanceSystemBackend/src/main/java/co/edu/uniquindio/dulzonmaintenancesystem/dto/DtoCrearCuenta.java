package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoCrearCuenta(
        @Email(message = "El correo debe tener un formato válido.")
        @NotBlank(message = "El email no puede estar vacío.")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía.")
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres.")
        String password,

        @NotBlank @NotBlank(message = "El rol no puede estar vacio")
        Rol rol,

        @NotBlank @NotBlank(message = "El rol no puede estar vacio")
        EstadoCuenta estadoCuenta
) {

}
