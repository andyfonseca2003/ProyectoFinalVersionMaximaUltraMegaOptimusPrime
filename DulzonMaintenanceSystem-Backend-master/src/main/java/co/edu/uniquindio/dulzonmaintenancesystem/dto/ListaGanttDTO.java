package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import java.time.LocalDateTime;

public record ListaGanttDTO(

        String nombreCartaGantt,
        String idCartaGantt,

        LocalDateTime fechaCreacion

) {
}
