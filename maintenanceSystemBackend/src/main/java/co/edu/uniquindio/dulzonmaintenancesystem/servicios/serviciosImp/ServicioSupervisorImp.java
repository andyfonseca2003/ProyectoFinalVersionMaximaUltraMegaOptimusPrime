package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.ActividadMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.CartaGantt;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Mantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Observacion;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.maquina.Maquina;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento.RepositorioCartaGantt;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento.RepositorioMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina.RepositorioMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosSupervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioSupervisorImp implements ServiciosSupervisor {
    private final RepositorioMantenimiento repositorioMantenimiento;
    private final RepositorioMaquina repositorioMaquina;
    private final RepositorioCartaGantt repositorioCartaGantt;


    @Override
    public List<Observacion> registrarObservacion() {
        return List.of();
    }

    @Override
    public void crearMaquina(MaquinaDTO maquinaDTO) {
        // Convertir el MaquinaDTO a una entidad Maquina
        Maquina maquina = new Maquina();
        maquina.setFechaAdquisicion(maquinaDTO.fechaAdquisicion());
        maquina.setDescripcion(maquinaDTO.descripcion());
        maquina.setUltimoMantenimiento(maquinaDTO.ultimoMantenimiento());
        maquina.setUbicacion(maquinaDTO.ubicacion());
        maquina.setTipoMaquina(maquinaDTO.tipoMaquina());
        maquina.setEstadoMaquina(maquinaDTO.estadoMaquina());

        // Guardar la máquina en la base de datos
        repositorioMaquina.save(maquina);
    }


    /**
     * Servicio para iniciar una actividad (Modifica el atributo de fecha inicio real en la cual se inicio la actividad)
     *
     * @param idCartaGantt
     * @param idActividad
     */
    @Override
    public void iniciarActividad(String idCartaGantt, String idActividad) {
        Optional<CartaGantt> cartaGanttOpt = repositorioCartaGantt.findById(idCartaGantt);
        if (cartaGanttOpt.isEmpty()) {
            throw new IllegalArgumentException("La carta Gantt especificada no existe.");
        }

        CartaGantt cartaGantt = cartaGanttOpt.get();
        ActividadMantenimiento actividad = buscarActividadEnCartaGantt(cartaGantt, idActividad);

        actividad.setFechaInicioReal(LocalDateTime.now());

        repositorioCartaGantt.save(cartaGantt);
    }


    /**
     * Servicio para finalizar una actividad (Modifica el atributo de fecha final real en la cual se inicio la actividad)
     *
     * @param idCartaGantt
     * @param idActividad
     */
    @Override
    public void finalizarActividad(String idCartaGantt, String idActividad) {
        Optional<CartaGantt> cartaGanttOpt = repositorioCartaGantt.findById(idCartaGantt);
        if (cartaGanttOpt.isEmpty()) {
            throw new IllegalArgumentException("La carta Gantt especificada no existe.");
        }

        CartaGantt cartaGantt = cartaGanttOpt.get();
        ActividadMantenimiento actividad = buscarActividadEnCartaGantt(cartaGantt, idActividad);

        actividad.setFechaFinReal(LocalDateTime.now());

        repositorioCartaGantt.save(cartaGantt);
    }


    /**
     * Servicio para iniciar una actividad (Modifica el atributo de fecha inicio real)
     *
     * @param idMantenimiento
     */
    @Override
    public void iniciarMantenimiento(String idMantenimiento) {
        Optional<Mantenimiento> mantenimentoOpt = repositorioMantenimiento.findById(idMantenimiento);
        if (mantenimentoOpt.isEmpty()) {
            throw new IllegalArgumentException("El mantenimiento especificado no existe.");
        }
        Mantenimiento mantenimiento = mantenimentoOpt.get();
        mantenimiento.setFechaInicioReal(LocalDateTime.now());

        repositorioMantenimiento.save(mantenimiento);
    }

    /**
     * Servicio para finalizar una actividad ( Mpodifica el atributo de fecha final real)
     *
     * @param idMantenimiento
     */
    @Override
    public void finalizarMantenimiento(String idMantenimiento) {
        Optional<Mantenimiento> mantenimentoOpt = repositorioMantenimiento.findById(idMantenimiento);
        if (mantenimentoOpt.isEmpty()) {
            throw new IllegalArgumentException("El mantenimiento especificado no existe.");
        }
        Mantenimiento mantenimiento = mantenimentoOpt.get();
        mantenimiento.setFechaFinReal(LocalDateTime.now());

        repositorioMantenimiento.save(mantenimiento);

    }

    /**
     * Metodo auxiliar para buscar una actividad dentro de un mantenimiento.
     *
     * @param idActividad
     * @return
     */
    private ActividadMantenimiento buscarActividadEnCartaGantt(CartaGantt cartaGantt, String idActividad) {
        return cartaGantt.getActividadesPlanificadas().stream()
                .filter(actividad -> actividad.getIdActividadMantenimiento().equals(idActividad))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("La actividad especificada no existe en la carta Gantt."));
    }

}
