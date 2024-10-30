package co.edu.uniquindio.dulzonmaintenancesystem.dto;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Cuadrilla;

import java.util.List;

public record DtoCrearEmpresaMantenimiento(
        String nombreEmpresa,
        String telefono,
        String email,
        String direccion,
        List<Cuadrilla> cuadrillAsignada

) {
}

