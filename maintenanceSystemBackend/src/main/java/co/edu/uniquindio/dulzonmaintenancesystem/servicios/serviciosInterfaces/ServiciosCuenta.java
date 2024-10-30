package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;


public interface ServiciosCuenta {
    String crearCuenta(DtoCrearCuenta cuenta, DtoCrearPersona persona) throws Exception;
    String editarCuenta(DtoEditarCuenta editarCuenta, String idCuenta) throws Exception;
    TokenDTO iniciarSesion(DtoLogin login) throws Exception;
    DtoObtenerInformacionCuenta obtenerInformacionCuenta(String idCuenta) throws Exception;

}
