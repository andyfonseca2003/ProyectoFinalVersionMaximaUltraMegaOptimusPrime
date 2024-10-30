package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DtoCrearCartaGantt(
        @NotNull String nombreCartaGantt,
        LocalDateTime fechaCreacion
) {
}