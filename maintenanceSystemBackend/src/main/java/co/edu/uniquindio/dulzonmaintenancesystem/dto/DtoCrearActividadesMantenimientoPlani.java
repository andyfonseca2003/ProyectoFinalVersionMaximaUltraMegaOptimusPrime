package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import java.time.LocalDateTime;

public record DtoCrearActividadesMantenimientoPlani(
        String idActividadMantenimiento,
        String nombre,
        String idOperador,
        String descripcion,
        LocalDateTime fechaInicioPlanificada,
        LocalDateTime fechaFinPlanificada,
        LocalDateTime fechaInicioReal,
        LocalDateTime fechaFinReal
) {
}
