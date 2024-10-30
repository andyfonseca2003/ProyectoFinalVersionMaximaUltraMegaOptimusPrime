package co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.TipoObservacion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Observacion {
    private String descripcion;

}
