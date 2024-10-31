package co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.Mantenimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioMantenimiento extends MongoRepository<Mantenimiento, String> {
    Optional<Mantenimiento> findByIdCartaGantt(String idCartaGantt);
}
