package co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Persona {
    @Id
    private String cedula;
    private String nombre;
    private String Apellido;
    private String telefono;
    private String direccion;
    private LocalDateTime fechaNacimiento;
}
