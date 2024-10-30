package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.maquina.Maquina;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina.RepositorioMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosMaquina;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioMaquinaImp implements ServiciosMaquina {

    private final RepositorioMaquina repositorioMaquina;

    @Override
    public List<MaquinaDTO> obtenerMaquinas() throws Exception {
        List<Maquina> maquinas = repositorioMaquina.findAll();
        if (maquinas.isEmpty()) {
            throw new Exception("Ninguna maquina encontrada");
        }
        List<MaquinaDTO> listaMaquinas = new ArrayList<>();
        for (Maquina maquina : maquinas) {
            listaMaquinas.add(new MaquinaDTO(
                    maquina.getIdMaquina(),
                    maquina.getFechaAdquisicion(),
                    maquina.getDescripcion(),
                    maquina.getUltimoMantenimiento(),
                    maquina.getUbicacion(),
                    maquina.getTipoMaquina(),
                    maquina.getEstadoMaquina()
            ));
        }
        return listaMaquinas;
    }
}
