package co.edu.uniquindio.dulzonmaintenancesystem.Gerente;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.Rol;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.DtoEditarCuentaAdmi;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosGerente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class GerenteTest {
    @Autowired
    private ServiciosGerente serviciosGerente;


    @Test
    public void editCuentatest() {
        String idCuenta = "67148ff7a61e465537510399";
        DtoEditarCuentaAdmi dtoEditarCuentaAdmi = new DtoEditarCuentaAdmi(
                EstadoCuenta.ACTIVA,
                Rol.GERENTE,
                "Camilo",
                "Sanchez"
        );
        assertDoesNotThrow(() -> {
            serviciosGerente.editarCuentaAdmin(dtoEditarCuentaAdmi, idCuenta); // Actualiza la cuenta
        });
    }

    @Test
    public void eliminarCuentaAdmi() {
        String idCuenta = "6713e827bf032a4d88bffc6b";
        assertDoesNotThrow(() -> {
            serviciosGerente.eliminarCuentaAdmi(idCuenta);
        });
    }
}
