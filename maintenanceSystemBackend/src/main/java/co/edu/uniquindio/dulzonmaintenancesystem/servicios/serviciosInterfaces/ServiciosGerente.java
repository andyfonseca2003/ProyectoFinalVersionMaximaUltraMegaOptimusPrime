package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces;


import co.edu.uniquindio.dulzonmaintenancesystem.dto.DtoEditarCuentaAdmi;

public interface ServiciosGerente {
    void visualizarMantenimiento(String idMantenimiento);

    void visulizarObservacionesMantenimienti(String idMantenimiento);

    void visualizarProgramacionMantenimiento(String idMantenimiento);

    String editarCuentaAdmin(DtoEditarCuentaAdmi dtoEditarCuentaAdmi, String idCuenta) throws Exception;

    String eliminarCuentaAdmi(String idCuenta) throws Exception;
}
