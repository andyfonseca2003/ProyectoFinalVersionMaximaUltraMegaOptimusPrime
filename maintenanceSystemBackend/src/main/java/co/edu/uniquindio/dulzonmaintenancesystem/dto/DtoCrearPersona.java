package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import java.time.LocalDateTime;

public record DtoCrearPersona(
        String cedula,
        String nombre,
        String Apellido,
        String telefono,
        String direccion,
        LocalDateTime fechaNacimiento
) {


}
