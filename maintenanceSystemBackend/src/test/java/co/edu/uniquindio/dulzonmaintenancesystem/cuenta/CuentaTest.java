package co.edu.uniquindio.dulzonmaintenancesystem.cuenta;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.Rol;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp.ServiciosCuentaImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CuentaTest {

    @Autowired
    private ServiciosCuentaImp serviciosCuentaImp;


    @Test
    public void crearCuenta() {
        DtoCrearCuenta nuevaCuentaDTO = new DtoCrearCuenta(

                "brandon123@gmail.com.com",
                "123",
                Rol.SUPERVISOR,
                EstadoCuenta.ACTIVA


        );
        DtoCrearPersona nuevaPersonaDTO = new DtoCrearPersona(
                "10012774338",
                "Camio",
                "Sepulveda",
                "3153032",
                "mariacristina",
                LocalDateTime.of(2000, 8, 17, 18, 0)
        );
        assertDoesNotThrow(() -> {
            String id = serviciosCuentaImp.crearCuenta(nuevaCuentaDTO, nuevaPersonaDTO);
            // Verifica que el id de la cuenta creada no sea nulo
            assertNotNull(id);
        });
    }


    @Test
    public void editarCuenta() {
        String idCuenta = "6712c2e14b3f93460e9efe69";
        DtoEditarCuenta dtoEditarCuenta = new DtoEditarCuenta(
                "3153034",
                "carrera#-_sa112",
                "123456"

        );

        // Se espera que no se lance ninguna excepción al editar la cuenta
        assertDoesNotThrow(() -> {
            serviciosCuentaImp.editarCuenta(dtoEditarCuenta, idCuenta); // Actualiza la cuenta
        });
    }

    @Test
    public void iniciarSesion() {
        String email = "camiloAas@gmail.com";
        String password = "123456";  // Contraseña válida

        DtoLogin createLoginDTO = new DtoLogin(email, password);

        assertDoesNotThrow(() -> {
            TokenDTO tokenDTO = serviciosCuentaImp.iniciarSesion(createLoginDTO);
            // Imprimir el token en la consola
            System.out.println("Token generado: " + tokenDTO.token());
        });
    }

    @Test
    public void obtenerInformacionCuenta() {
        String idCuenta = "67148ff7a61e465537510399";
        assertDoesNotThrow(() -> {
            System.out.println(serviciosCuentaImp.obtenerInformacionCuenta(idCuenta));
        });
    }

}
