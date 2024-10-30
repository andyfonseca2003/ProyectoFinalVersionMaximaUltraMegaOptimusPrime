package co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.Retraso;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document("HistorialMantenimiento") // Revisar el diagrama de clase si es o no un documento de mongoDB
public class HistorialMantenimiento {
    private String idRegistro;
    private String idMantenimiento;
    private String idMaquina;
    private String idCuadrilla;
    private String idCartaGantt;
    private LocalDate fecha;
    private Retraso retraso;
    private List<Observacion> observaciones;



}
