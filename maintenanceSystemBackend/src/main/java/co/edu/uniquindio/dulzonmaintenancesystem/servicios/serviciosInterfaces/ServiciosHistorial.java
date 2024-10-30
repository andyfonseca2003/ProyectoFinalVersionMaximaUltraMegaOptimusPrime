package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces;

import java.time.LocalDateTime;

public interface ServiciosHistorial {

    void calcularRetraso(LocalDateTime horaFinalPlanificada, LocalDateTime horaFinalReal);

}
