package co.edu.uniquindio.dulzonmaintenancesystem.controller;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosOperador;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sinAuth")
public class sinAutenticacionController {

    private final ServiciosCuenta serviciosCuenta;
    private  final ServiciosOperador   serviciosOperador;

    @PostMapping("/login")
    public ResponseEntity<MessageDTO<TokenDTO>> iniciarSesion(@Valid @RequestBody DtoLogin login) throws Exception{
        TokenDTO token = serviciosCuenta.iniciarSesion(login);
        return ResponseEntity.ok(new MessageDTO<>(false, token));
    }

    @GetMapping("/obtener-mantenimientos")
    public ResponseEntity<List<ListaMantenimientoDTO>> obtenerMantenimientos() {
        List<ListaMantenimientoDTO> manntenimientos = serviciosOperador.listarMantenimientos();
        return ResponseEntity.ok(manntenimientos); // Retorna la lista de DTOs con un estado HTTP 200 OK
    }

    @GetMapping("/obtener-lista-gantt")
    public ResponseEntity<List<ListaGanttDTO>> listarGantt() {
        List<ListaGanttDTO> Gantts = serviciosOperador.ListarGanttDT();
        return ResponseEntity.ok(Gantts); // Retorna la lista de DTOs con un estado HTTP 200 OK
    }


}

