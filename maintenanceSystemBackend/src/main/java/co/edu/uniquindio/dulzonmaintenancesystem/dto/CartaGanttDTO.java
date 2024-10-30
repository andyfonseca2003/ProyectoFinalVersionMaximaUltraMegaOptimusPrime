package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import java.time.LocalDateTime;

public record CartaGanttDTO(
        String idCartaGantt,
        String nombreCartaGantt,
        LocalDateTime fechaCreacion

) {
}
