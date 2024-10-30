package co.edu.uniquindio.dulzonmaintenancesystem.Operador;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.DtoCrearCartaGantt;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Cuadrilla;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.Trabajador;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.ActividadMantenimiento;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp.ServicioOperadorImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OperadorTest {

    @Autowired
    private ServicioOperadorImp servicioOperadorImp;


    @Test
    public void crearCartaGantt() {
        // Preparar datos de prueba
        List<Trabajador> trabajadores = new ArrayList<>();
        trabajadores.add(new Trabajador(1, "Juan", "Electricista", "empresa1"));
        trabajadores.add(new Trabajador(2, "Ana", "Plomera", "empresa2"));

        List<Cuadrilla> cuadrillas = new ArrayList<>();
        cuadrillas.add(new Cuadrilla("1234","cuadrilla1",trabajadores));

        List<ActividadMantenimiento> actividades = new ArrayList<>();
        actividades.add(new ActividadMantenimiento("1", "Reparación de tuberías", "1", "Reparar tuberías",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), null, null));

        DtoCrearCartaGantt cartaGanttDTO = new DtoCrearCartaGantt(actividades,cuadrillas,LocalDateTime.now(),"proyecto1");
        servicioOperadorImp.crearCartaGantt(cartaGanttDTO);

        // Aquí puedes agregar aserciones para verificar que el objeto fue creado correctamente
        assertNotNull(cartaGanttDTO); // Asegúrate de que el DTO no es nulo
    }


    @Test
    public void editarCartaGantt(){
        String idCartaGantt = "6714b40d5fbf905a1f927f6b";
        // Preparar datos de prueba
        List<Trabajador> trabajadores = new ArrayList<>();
        trabajadores.add(new Trabajador(1, "pablo", "Electricista", "empresa1"));
        trabajadores.add(new Trabajador(2, "San", "Plomera", "empresa2"));

        List<Cuadrilla> cuadrillas = new ArrayList<>();
        cuadrillas.add(new Cuadrilla("2010","cuadrilla1",trabajadores));

        List<ActividadMantenimiento> actividades = new ArrayList<>();
        actividades.add(new ActividadMantenimiento("1", "Reparación de tuberías", "1", "Reparar tuberías",
                LocalDateTime.now(), LocalDateTime.now().plusDays(1), null, null));

        DtoCrearCartaGantt cartaGanttDTO = new DtoCrearCartaGantt(actividades,cuadrillas,LocalDateTime.now(),"ProyectPrueba1");
        servicioOperadorImp.editarCartaGantt(idCartaGantt,cartaGanttDTO);

        // Aquí puedes agregar aserciones para verificar que el objeto fue creado correctamente
        assertNotNull(cartaGanttDTO); // Asegúrate de que el DTO no es nulo
    }

    @Test
    public void eliminarCartaGantt(){
        String idCartaGannt = "6714b40d5fbf905a1f927f6b";
        servicioOperadorImp.eliminarCartaGantt(idCartaGannt);
    }




}
