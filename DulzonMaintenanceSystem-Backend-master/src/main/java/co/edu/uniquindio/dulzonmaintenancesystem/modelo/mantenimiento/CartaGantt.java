package co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Cuadrilla;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Document("CartaGantt")
public class CartaGantt {

    @Id
    private String idCartaGantt;
    private String idOperador;
    private String idEmpresa;
    private List<ActividadMantenimiento> actividadesPlanificadas;
    private List<Cuadrilla> cuadrillas;
    private LocalDateTime fechaCreacion;
    private String nombreCartaGantt;


}
