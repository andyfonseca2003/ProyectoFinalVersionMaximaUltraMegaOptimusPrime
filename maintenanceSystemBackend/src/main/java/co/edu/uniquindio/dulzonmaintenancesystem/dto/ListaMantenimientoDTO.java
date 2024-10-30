package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMantenimiento;

import java.time.LocalDateTime;

public record ListaMantenimientoDTO(

        String idMaquina,
        String idSupervisor,
        String nombre,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        LocalDateTime fechaInicioReal, // puede ser null
        LocalDateTime fechaFinReal,
        EstadoMantenimiento estadoMantenimiento,
        String idCartaGantt


) {
}
