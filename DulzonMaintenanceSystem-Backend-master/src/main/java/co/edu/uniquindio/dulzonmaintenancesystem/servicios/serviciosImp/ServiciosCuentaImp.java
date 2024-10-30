package co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosImp;

import co.edu.uniquindio.dulzonmaintenancesystem.Configuracion.JWTUtils;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.Exception.Cuenta.*;
import co.edu.uniquindio.dulzonmaintenancesystem.dto.*;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios.Cuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios.Persona;
import co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosUsuarios.RepositorioCuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.servicios.serviciosInterfaces.ServiciosCuenta;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiciosCuentaImp implements ServiciosCuenta {

    private final RepositorioCuenta repositorioCuenta;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    private boolean existsEmail(String email) {
        return repositorioCuenta.findByEmail(email).isPresent();
    }

    private boolean existsCedula(String cedula) {
        return repositorioCuenta.findByCedula(cedula).isPresent();
    }

    @Override
    public String crearCuenta(DtoCrearCuenta cuenta, DtoCrearPersona persona) {
        if (existsEmail(cuenta.email())) {
            throw new EmailAlreadyExistsException("El email ya existe");
        }

        if (existsCedula((persona.cedula()))) {
            throw new CedulaAlreadyExistsException("La cedula ya existe");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = bCryptPasswordEncoder.encode(cuenta.password());

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setEmail(cuenta.email());
        nuevaCuenta.setPassword(hashedPassword);
        nuevaCuenta.setRol(cuenta.rol());
        nuevaCuenta.setEstadoCuenta(cuenta.estadoCuenta());
        nuevaCuenta.setRegistrationDate(LocalDateTime.now());

        nuevaCuenta.setPersona(new Persona(
                persona.cedula(),
                persona.nombre(),
                persona.Apellido(),
                persona.telefono(),
                persona.direccion(),
                persona.fechaNacimiento()
        ));

        Cuenta crearCuenta = repositorioCuenta.save(nuevaCuenta);
        return crearCuenta.getEmail();
    }

    /**
     * Metodo para editar los datos basicos de la cuenta.
     *
     * @param cuenta
     * @param idCUenta
     * @return
     * @throws AccountNotFoundException
     */
    @Override
    public String editarCuenta(DtoEditarCuenta cuenta, String idCUenta) throws AccountNotFoundException {
        Optional<Cuenta> cuentaOptional = repositorioCuenta.findById(idCUenta);
        if (cuentaOptional.isEmpty()) {
            throw new AccountNotFoundException("El usuario no fue encontrado");
        }

        Cuenta cuentaActualizada = cuentaOptional.get();


        cuentaActualizada.getPersona().setTelefono(cuenta.telefono());
        cuentaActualizada.getPersona().setDireccion(cuenta.direccion());

        String newPassword = cuenta.password();
        if (newPassword != null && !newPassword.isEmpty()) {
            cuentaActualizada.setPassword(encryptPassword(newPassword));
        }
        repositorioCuenta.save(cuentaActualizada);
        return cuentaActualizada.getIdCuenta();
    }

    @Override
    public TokenDTO iniciarSesion(DtoLogin login) throws Exception {
        // Buscar la cuenta en la base de datos utilizando el email proporcionado en el LoginDTO.
        Optional<Cuenta> optionalCuenta = repositorioCuenta.findByEmail(login.email());

        // Si no se encuentra una cuenta asociada al email, lanzar una excepción personalizada.
        if (optionalCuenta.isEmpty()) {
            throw new EmailNotFoundException("No se encontro el email");
        }

        // Obtener la cuenta de la Optional. Aquí se asume que la cuenta está presente, ya que se verificó antes.
        Cuenta cuenta = optionalCuenta.get();

        // Verificar el estado de la cuenta. Si no está activa, lanzar una excepción personalizada.
        if (!cuenta.getEstadoCuenta().equals(EstadoCuenta.ACTIVA)) {
            throw new ActiveAccountException("La cuenta no se encuentra Activada. Por favor, actívala para ingresar!");
        }

        // Verificar si la contraseña proporcionada coincide con la contraseña almacenada utilizando el passwordEncoder.
        if (!passwordEncoder.matches(login.password(), cuenta.getPassword())) {
            throw new InvalidPasswordException("la contraseña es incorrecta"); // Lanzar excepción si la contraseña es incorrecta.
        }

        // Construir los claims necesarios para generar el token JWT, pasando la cuenta encontrada.
        Map<String, Object> map = construirClaims(cuenta);

        // Generar y devolver un nuevo TokenDTO que contiene el token JWT generado.
        return new TokenDTO(jwtUtils.generarToken(cuenta.getEmail(), map));

    }

    @Override
    public DtoObtenerInformacionCuenta obtenerInformacionCuenta(String idCuenta) throws AccountNotFoundException {
        Optional<Cuenta> cuentaOptional = repositorioCuenta.findById(idCuenta);
        if (cuentaOptional.isEmpty()) {
            throw new AccountNotFoundException("No se encontro la cuenta");
        }

        Cuenta cuenta = cuentaOptional.get();

        return new DtoObtenerInformacionCuenta(
                cuenta.getPersona().getCedula(),
                cuenta.getPersona().getNombre(),
                cuenta.getPersona().getApellido(),
                cuenta.getPersona().getTelefono(),
                cuenta.getPersona().getDireccion(),
                cuenta.getPersona().getFechaNacimiento(),
                cuenta.getEmail()
        );

    }

    private Map<String, Object> construirClaims(Cuenta cuenta) {
        return Map.of(
                "rol", cuenta.getRol(),
                "nombre", cuenta.getPersona().getNombre(),
                "id", cuenta.getIdCuenta()
        );
    }


    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }


}
