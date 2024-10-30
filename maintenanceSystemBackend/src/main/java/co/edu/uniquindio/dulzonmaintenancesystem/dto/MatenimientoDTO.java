package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Observacion;

import java.time.LocalDateTime;
import java.util.List;

public record MatenimientoDTO(

        String idMaquina,
        String idSupervisor,
        String nombre,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        LocalDateTime fechaInicioReal, // puede ser null
        LocalDateTime fechaFinReal,
        List<Observacion> observaciones,
        EstadoMantenimiento estadoMantenimiento,
        String idCartaGantt


) {

}
