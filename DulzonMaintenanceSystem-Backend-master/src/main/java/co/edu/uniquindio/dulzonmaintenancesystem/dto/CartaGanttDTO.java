package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CartaGanttDTO(
        String idCartaGantt,
        String nombreCartaGantt,
        LocalDateTime fechaCreacion

) {
}
