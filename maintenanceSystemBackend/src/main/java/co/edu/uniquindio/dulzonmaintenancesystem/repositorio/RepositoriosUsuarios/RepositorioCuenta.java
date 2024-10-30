package co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosUsuarios;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios.Cuenta;
import co.edu.uniquindio.dulzonmaintenancesystem.modelo.usuarios.Persona;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioCuenta extends MongoRepository<Cuenta, String> {
    Optional<Cuenta> findByEmail(String email);
    @Query("{'_id': ?0}")
    Optional<Persona> findByCedula(String cedula);
}
