package co.edu.uniquindio.dulzonmaintenancesystem.controller;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosOperador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operador")
public class MaquinaController {

    private final ServiciosOperador serviciosOperador;

    @PostMapping("/programar-mantenimiento")
    public ResponseEntity<MessageDTO<String>> programarMantenimiento(@Valid @RequestBody MatenimientoDTO mantenimientoDto) throws Exception {
        serviciosOperador.programarMantenimiento(mantenimientoDto);
        return ResponseEntity.ok(new MessageDTO<>(false, "El mantenimiento se creo exitosamente"));
    }

    @PostMapping("/registrar-actividad")
    public ResponseEntity<MessageDTO<String>> registrarActividad(@Valid @RequestBody ActividadDTO actividadDTO) {
        serviciosOperador.registarActividadmantenimiento(actividadDTO);
        return ResponseEntity.ok(new MessageDTO<>(false, "La Actividad se asigno (registro) exitosamente"));

    }

    @PostMapping("/crear-carta-Gantt")
    public ResponseEntity<Map<String, String>> crearCartaGantt(@Valid @RequestBody DtoCrearCartaGantt cartaGantt) {
        String cartaGanttId = serviciosOperador.crearCartaGantt(cartaGantt);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("respuesta", cartaGanttId);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtener-carta-gantt/{idCartGannt}")
    public DtoCrearCartaGantt obtenerCartasGanttEspecifica(@PathVariable String idCartGannt) {
        DtoCrearCartaGantt dtoCrearCartaGantt = serviciosOperador.obtenerCartasGanttEspecifica(idCartGannt);
        return dtoCrearCartaGantt;
    }

    @PutMapping("/editar-gantt/{idCartaGantt}")
    public String editarGantt(@Valid @RequestBody DtoEditarCartaGantt cartaGanttActualizada) throws Exception {
        String cartaGanttId = serviciosOperador.editarCartaGantt(cartaGanttActualizada);
        return cartaGanttId;
    }


}