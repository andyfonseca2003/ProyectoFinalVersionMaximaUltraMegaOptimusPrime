package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Cuadrilla;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.ActividadMantenimiento;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DtoEditarCartaGantt(
        @NotNull String idCartaGantt,
        @NotNull String nombreCartaGantt,
        List<Cuadrilla> cuadrillas,
        List<ActividadMantenimiento> actividadesPlanificadas
) {
}