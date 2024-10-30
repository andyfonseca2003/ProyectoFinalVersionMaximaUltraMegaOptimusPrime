package co.edu.uniquindio.dulzonmaintenancesystem.repositorio.RepositorioEmpresasExternas;

import co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna.EmpresaMantenimiento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEmpresaExterna extends MongoRepository<EmpresaMantenimiento, String> {
}
