package co.edu.uniquindio.dulzonmaintenancesystem.controller;

import co.edu.uniquindio.dulzonmaintenancesystem.dto.InfoSupervisorDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosMaquina;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuenta")
public class CuentaController {

    private final ServiciosCuenta serviciosCuenta;

    @GetMapping("/find-supervisores")
    public ResponseEntity<List<InfoSupervisorDTO>> obtenerSupervisores() throws Exception {
        List<InfoSupervisorDTO> supervisores = serviciosCuenta.obtenerSupervisores();
        return ResponseEntity.ok(supervisores); // Retorna la lista de DTOs con un estado HTTP 200 OK
    }

}