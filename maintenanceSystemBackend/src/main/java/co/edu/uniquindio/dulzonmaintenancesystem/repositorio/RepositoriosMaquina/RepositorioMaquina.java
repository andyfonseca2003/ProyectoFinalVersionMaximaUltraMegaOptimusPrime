package co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositoriosMaquina;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.maquina.Maquina;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioMaquina extends MongoRepository<Maquina, String> {
}
