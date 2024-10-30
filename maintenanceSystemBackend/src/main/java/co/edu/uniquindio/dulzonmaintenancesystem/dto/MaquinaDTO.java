package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.TipoMaquina;

import java.time.LocalDateTime;

public record MaquinaDTO(

        LocalDateTime fechaAdquisicion,
        String descripcion,
        LocalDateTime ultimoMantenimiento,
        String ubicacion,
        TipoMaquina tipoMaquina,
        EstadoMaquina estadoMaquina
) {
}

