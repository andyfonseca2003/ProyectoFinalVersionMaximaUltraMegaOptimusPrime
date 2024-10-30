package co.edu.uniquindio.dulzonmaintenancesystem.maquina;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.TipoMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.MaquinaDTO;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina.RepositorioMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosSupervisor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MaquinaTest {

    @Autowired
    private ServiciosSupervisor serviciosSupervisor;
    @Autowired
    private RepositorioMaquina repositorioMaquina;

    @Test
    public void crearMaquinaTest() {
        // Crear un MaquinaDTO para la prueba
        MaquinaDTO maquinaDTO = new MaquinaDTO(
                LocalDateTime.now(),
                "Descripción de la máquina de prueba",
                LocalDateTime.now().minusMonths(1),
                "Ubicación de prueba",
                TipoMaquina.DESHUESADORA,
                EstadoMaquina.FUNCIONANDO
        );

        // Llamar al método que estamos probando
        serviciosSupervisor.crearMaquina(maquinaDTO);
    }
}
