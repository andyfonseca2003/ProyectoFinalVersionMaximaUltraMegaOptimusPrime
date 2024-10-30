package co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMantenimiento;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.mantenimiento.CartaGantt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCartaGantt extends MongoRepository<CartaGantt, String> {
}
