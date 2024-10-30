package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Exception.Cuenta.InvalidPasswordException;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.DtoEditarCuentaAdmi;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.DtoPassword;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios.Cuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosUsuarios.RepositorioCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosGerente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServicioGerenteImp implements ServiciosGerente {

    private final RepositorioCuenta repositorioCuenta;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void visualizarMantenimiento(String idMantenimiento) {

    }

    @Override
    public void visulizarObservacionesMantenimienti(String idMantenimiento) {

    }

    @Override
    public void visualizarProgramacionMantenimiento(String idMantenimiento) {

    }

    @Override
    public String editarCuentaAdmin(DtoEditarCuentaAdmi cuentaEdit, String idCuenta) throws AccountNotFoundException {
        Optional<Cuenta> cuentaOptional = repositorioCuenta.findById(idCuenta);
        if (cuentaOptional.isEmpty()) {
            throw new AccountNotFoundException("El usuario no fue encontrado");
        }

        Cuenta cuenta = cuentaOptional.get();
        cuenta.setEstadoCuenta(cuentaEdit.estadoCuenta());
        cuenta.setRol(cuentaEdit.rol());

        repositorioCuenta.save(cuenta);
        return cuenta.getIdCuenta();
    }

    @Override
    public String eliminarCuentaAdmi(String idCuenta) throws AccountNotFoundException {
        Optional<Cuenta> cuentaOptional = repositorioCuenta.findById(idCuenta);
        if (cuentaOptional.isEmpty()) {
            throw new AccountNotFoundException("El usuario no fue encontrado");
        }

        Cuenta cuenta = cuentaOptional.get();
        cuenta.setEstadoCuenta(EstadoCuenta.INACTIVA);
        repositorioCuenta.save(cuenta);
        return cuenta.getIdCuenta();
    }
}
