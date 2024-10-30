package co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ActividadMantenimiento {

    private String idActividadMantenimiento;
    private String nombre;
    private String IdOperador;
    private String descripcion;
    private LocalDateTime fechaInicioPlanificada;
    private LocalDateTime fechaFinPlanificada;
    private LocalDateTime fechaInicioReal; // puede ser null
    private LocalDateTime fechaFinReal; // puede ser null
}
