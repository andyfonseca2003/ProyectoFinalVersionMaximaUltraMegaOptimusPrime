package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.Exception.CartaGantt.CartaGanttNotFoundException;
import co.edu.uniquindio.dulzonmaintenancesystem.Exception.Mantenimiento.MaquinaNoEspecificadaExepcion;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Cuadrilla;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Trabajador;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.ActividadMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.CartaGantt;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Mantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Observacion;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento.RepositorioCartaGantt;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento.RepositorioMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina.RepositorioMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosOperador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicioOperadorImp implements ServiciosOperador {

    private final RepositorioMantenimiento repositorioMantenimiento;
    private final RepositorioMaquina repositorioMaquina;
    private final RepositorioCartaGantt repositorioCartaGantt;

    @Override
    public String crearCartaGantt(DtoCrearCartaGantt cartaGantt) {
        // Crear la nueva carta Gantt
        CartaGantt nuevaCartaGantt = new CartaGantt();
        nuevaCartaGantt.setNombreCartaGantt(cartaGantt.nombreCartaGantt());
        nuevaCartaGantt.setFechaCreacion(LocalDateTime.now());

        CartaGantt crearCuentaGannt = repositorioCartaGantt.save(nuevaCartaGantt);
        return crearCuentaGannt.getIdCartaGantt();

    }

    @Override
    public String editarCartaGantt(DtoEditarCartaGantt cartaGanttActualizada) throws CartaGanttNotFoundException {
        // Buscar la carta Gantt por ID
        Optional<CartaGantt> cartaGanttExistente = repositorioCartaGantt.findById(cartaGanttActualizada.idCartaGantt());

        if (cartaGanttExistente.isEmpty()) {
            throw new CartaGanttNotFoundException("No se encontró la carta Gantt con el ID: " + cartaGanttActualizada.idCartaGantt());
        }

        CartaGantt cartaGantt = cartaGanttExistente.get();

        // Actualizar el nombre de la carta Gantt si es necesario
        cartaGantt.setNombreCartaGantt(cartaGanttActualizada.nombreCartaGantt());

        // Actualizar las cuadrillas
        List<Cuadrilla> cuadrillasActualizadas = new ArrayList<>();
//        for (Cuadrilla cuadrillaDTO : cartaGanttActualizada.cuadrillas()) {
//            Cuadrilla cuadrilla = new Cuadrilla();
//            cuadrilla.setNombre(cuadrillaDTO.getNombre());
//
//            // Actualizar los trabajadores de la cuadrilla
//            List<Trabajador> trabajadoresActualizados = new ArrayList<>(cuadrillaDTO.getTrabajadores());
//            cuadrilla.setTrabajadores(trabajadoresActualizados);
//
//            cuadrillasActualizadas.add(cuadrilla);
//        }
//        cartaGantt.setCuadrillas(cuadrillasActualizadas);

        // Actualizar las actividades planificadas
//        List<ActividadMantenimiento> actividadesActualizadas =
//                new ArrayList<>(cartaGanttActualizada.actividadesPlanificadas());
//        cartaGantt.setActividadesPlanificadas(actividadesActualizadas);

        // Guardar la carta Gantt modificada
        CartaGantt cartaGanttModificada = repositorioCartaGantt.save(cartaGantt);

        // Devolver el ID de la carta Gantt modificada
        return cartaGanttModificada.getIdCartaGantt();
    }

    @Override
    public void programarMantenimiento(MatenimientoDTO mantenimientoDto) throws MaquinaNoEspecificadaExepcion {
        // Validar si la máquina existe
        if (!repositorioMaquina.existsById(mantenimientoDto.idMaquina())) {
            throw new MaquinaNoEspecificadaExepcion("La máquina especificada no existe.");
        }

        // Verificar que la fecha de inicio sea anterior a la fecha de fin
        if (mantenimientoDto.fechaInicio().isAfter(mantenimientoDto.fechaFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }

        // Convertir el DTO a una entidad Mantenimiento
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setIdMaquina(mantenimientoDto.idMaquina());
        mantenimiento.setIdSupervisor(mantenimientoDto.idSupervisor());
        mantenimiento.setFechaInicio(mantenimientoDto.fechaInicio());
        mantenimiento.setFechaFin(mantenimientoDto.fechaFin());
        if (mantenimientoDto.observaciones()==null) {
            List<Observacion> observacionesActualizadas = new ArrayList<>();
            observacionesActualizadas.add(new Observacion(mantenimientoDto.observaciones()));
            mantenimiento.setObservaciones(observacionesActualizadas);
        }
        mantenimiento.setEstadoMantenimiento(EstadoMantenimiento.EN_PROCESO);
        mantenimiento.setNombre(mantenimientoDto.nombre());
        mantenimiento.setIdCartaGantt(mantenimientoDto.idCartaGantt()); // Asociar la carta de Gantt al mantenimiento

        // Guardar el mantenimiento en la base de datos
        repositorioMantenimiento.save(mantenimiento);
    }

    @Override
    public void registarActividadmantenimiento(ActividadDTO actividadDTO) {
        // Verificar si la carta de Gantt existe
        Optional<Mantenimiento> mantenimientoOpt = repositorioMantenimiento.findByIdCartaGantt(actividadDTO.id());
        if (mantenimientoOpt.isEmpty()) {
            throw new IllegalArgumentException("El mantenimiento especificado no existe.");
        }

        // Obtener la carta de Gantt encontrada
        Mantenimiento mantenimiento = mantenimientoOpt.get();

        // Crear una nueva actividad a partir del DTO
        ActividadMantenimiento nuevaActividad = new ActividadMantenimiento();
        nuevaActividad.setIdActividadMantenimiento(UUID.randomUUID().toString()); // Generar id automáticamente
        nuevaActividad.setNombre(actividadDTO.nombre());
        nuevaActividad.setIdOperador(actividadDTO.IdOperador());
        nuevaActividad.setDescripcion(actividadDTO.descripcion());
        nuevaActividad.setFechaInicioPlanificada(actividadDTO.fechaInicioPlanificada());
        nuevaActividad.setFechaFinPlanificada(actividadDTO.fechaFinPlanificada());

        if(mantenimiento.getActividadesPlanificadas() ==  null){
            List<ActividadMantenimiento> actividadesPlanificadas = new ArrayList<>();
            actividadesPlanificadas.add(nuevaActividad);
            mantenimiento.setActividadesPlanificadas(actividadesPlanificadas);
        }else{
            // Agregar la nueva actividad a la carta de Gantt
            mantenimiento.getActividadesPlanificadas().add(nuevaActividad);
        }

        // Guardar la carta de Gantt actualizada en la base de datos
        repositorioMantenimiento.save(mantenimiento);
    }

    @Override
    public String eliminarCartaGantt(String idCartaGantt) {
        Optional<CartaGantt> optionalCartaGantt = repositorioCartaGantt.findById(idCartaGantt);
        if (optionalCartaGantt.isEmpty()) {
            throw new CartaGanttNotFoundException("No se encontró la carta Gantt con el ID: " + idCartaGantt);
        }

        CartaGantt cartaGantt = optionalCartaGantt.get();

        repositorioCartaGantt.delete(cartaGantt);

        return "La cartaGantt con id " + idCartaGantt + " fue eliminada correctamente.";
    }

    @Override
    public List<CartaGanttDTO> obtenerCartasGantt() {
        List<CartaGantt> cartasGantt = repositorioCartaGantt.findAll(); // Obtener todas las cartas Gantt
        List<CartaGanttDTO> cartasGanttDTO = new ArrayList<>();

        for (CartaGantt cartaGantt : cartasGantt) {
            CartaGanttDTO dto = new CartaGanttDTO(
                    cartaGantt.getIdCartaGantt(),
                    cartaGantt.getNombreCartaGantt(),
                    cartaGantt.getFechaCreacion()
            );

            cartasGanttDTO.add(dto);
        }

        return cartasGanttDTO; // Retorna la lista de DTOs
    }

    @Override
    public DtoCrearCartaGantt obtenerCartasGanttEspecifica(String idCartGannt) {
        Optional<CartaGantt> cartasGantt = repositorioCartaGantt.findById(idCartGannt); // Obtener todas las cartas Gantt

        CartaGantt cartaGantt = cartasGantt.get();
        DtoCrearCartaGantt dto = new DtoCrearCartaGantt(
                cartaGantt.getNombreCartaGantt(),
                cartaGantt.getFechaCreacion()
        );

        return dto; // Retorna el dto.
    }

    @Override
    public List<ListaGanttDTO> ListarGanttDT() {
        List<CartaGantt> cartasGantt = repositorioCartaGantt.findAll(); // Obtener todas las cartas Gantt
        List<ListaGanttDTO> listaGanttDTO = new ArrayList<>();

        for (CartaGantt cartaGantt : cartasGantt) {
            ListaGanttDTO dto = new ListaGanttDTO(
                    cartaGantt.getNombreCartaGantt(),
                    cartaGantt.getIdCartaGantt(),
                    cartaGantt.getFechaCreacion()
            );

            listaGanttDTO.add(dto);
        }

        return listaGanttDTO; // Retorna la lista de DTOs

    }

    @Override
    public List<ListaMantenimientoDTO> listarMantenimientos() {
        List<Mantenimiento> mantenimientos = repositorioMantenimiento.findAll(); // Obtener todos los mantenimientos
        List<ListaMantenimientoDTO> mantenimientoDTOS = new ArrayList<>();

        for (Mantenimiento mantenimiento : mantenimientos) {
            ListaMantenimientoDTO dto = new ListaMantenimientoDTO(
                    mantenimiento.getIdMaquina(),
                    mantenimiento.getIdSupervisor(),
                    mantenimiento.getNombre(),
                    mantenimiento.getFechaInicio(),
                    mantenimiento.getFechaFin(),
                    mantenimiento.getFechaInicioReal(),
                    mantenimiento.getFechaFinReal(),
                    mantenimiento.getEstadoMantenimiento(),
                    mantenimiento.getIdCartaGantt()
            );
            mantenimientoDTOS.add(dto);
        }

        return mantenimientoDTOS;
    }


}