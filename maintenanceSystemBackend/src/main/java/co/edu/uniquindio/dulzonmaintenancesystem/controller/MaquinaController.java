package co.edu.uniquindio.dulzonmaintenancesystem.controller;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosMaquina;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maquina")
public class MaquinaController {

    private final ServiciosMaquina serviciosMaquina;

    @GetMapping("/all")
    public ResponseEntity<List<MaquinaDTO>> obtenerMaquinas() throws Exception {
        List<MaquinaDTO> mantenimientos = serviciosMaquina.obtenerMaquinas();
        return ResponseEntity.ok(mantenimientos); // Retorna la lista de DTOs con un estado HTTP 200 OK
    }

}