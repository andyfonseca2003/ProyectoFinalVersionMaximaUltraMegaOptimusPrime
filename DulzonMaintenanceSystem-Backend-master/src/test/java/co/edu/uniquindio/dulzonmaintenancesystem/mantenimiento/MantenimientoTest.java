package co.edu.uniquindio.dulzonmaintenancesystem.mantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.ActividadDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.MatenimientoDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Observacion;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento.RepositorioMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina.RepositorioMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosOperador;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosSupervisor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MantenimientoTest {

    @Autowired
    private RepositorioMantenimiento repositorioMantenimiento;

    @Autowired
    private RepositorioMaquina repositorioMaquina;

    @Autowired
    private ServiciosOperador servicioOperador;

    @Autowired
    private ServiciosSupervisor serviciosSupervisor;

    //

    @Test
    public void programarMantenimientoTest() throws Exception {
        List<Observacion> observacionList = new ArrayList<>();
        observacionList.add(new Observacion("se realizo algo"));
        // Crear un MatenimientoDTO con datos válidos
        MatenimientoDTO mantenimientoDTO = new MatenimientoDTO(
                "6715952151929d013de17ed4",
                "67159598d9af864cd752d677",
                "Mantenimiento prueba1",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                null,
                null,
                observacionList,
                EstadoMantenimiento.EN_PAUSA,

                "67159574ec90532f82ef409c"
        );
        // Programar el mantenimiento
        servicioOperador.programarMantenimiento(mantenimientoDTO);
    }

    @Test
    public void registrarActividadMantenimientoTest() {

        String idMantenimienento= "67152569b140dd5ed6b27987";
        // Crear un DTO de actividad
        ActividadDTO actividadDTO = new ActividadDTO(

                "Cambio de Radiador",
                "67135b727c166e1ec58a5de6",
                "Realizar cambio de Radiador en la máquina.",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                null,
                null
        );

        // Registrar la actividad en el mantenimiento
        servicioOperador.registarActividadmantenimiento(idMantenimienento,actividadDTO);

}

    @Test
    public void iniciarMantenimientoTest(){
        String idMantenimiento= "67146f8d703ae618a1d12b18";
        serviciosSupervisor.iniciarMantenimiento(idMantenimiento);
    }

    @Test
    public void finalizarMantenimientoTest(){
        String idMantenimiento= "67146f8d703ae618a1d12b18";
        serviciosSupervisor.finalizarMantenimiento(idMantenimiento);
    }


    @Test
    public void iniciarActividadTest(){
        String idCartaGantt="6714b92f37fb95677ff28d97";
        String idActividad="6c1043fa-ab25-45da-9a72-b971fd8a5976";

        serviciosSupervisor.iniciarActividad(idCartaGantt,idActividad);
    }

    @Test
    public void finalizarActividadTest(){
        String idCartaGantt="6714b92f37fb95677ff28d97";
        String idActividad="6c1043fa-ab25-45da-9a72-b971fd8a5976";


        serviciosSupervisor.finalizarActividad(idCartaGantt,idActividad);
    }




}

