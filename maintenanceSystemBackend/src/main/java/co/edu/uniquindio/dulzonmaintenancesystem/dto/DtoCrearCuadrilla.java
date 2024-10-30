package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Trabajador;

import java.util.List;

public record DtoCrearCuadrilla(
        String nombre,
        List<Trabajador> trabajadores

) {
}

