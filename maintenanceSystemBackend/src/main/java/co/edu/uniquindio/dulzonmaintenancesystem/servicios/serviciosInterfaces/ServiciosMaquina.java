package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;

import java.util.List;


public interface ServiciosMaquina {
    List<MaquinaDTO> obtenerMaquinas() throws Exception;

}
