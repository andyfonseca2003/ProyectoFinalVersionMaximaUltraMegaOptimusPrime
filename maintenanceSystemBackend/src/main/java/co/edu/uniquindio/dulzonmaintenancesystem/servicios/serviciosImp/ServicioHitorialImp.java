package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosHistorial;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ServicioHitorialImp implements ServiciosHistorial {
    @Override
    public void calcularRetraso(LocalDateTime horaFinalPlanificada, LocalDateTime horaFinalReal) {

    }
}
