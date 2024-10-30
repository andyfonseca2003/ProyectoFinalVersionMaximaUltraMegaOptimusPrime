package co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document("EmpresaExterna")
public class EmpresaMantenimiento {

    @Id
    private String idEmpresa;
    private String nombreEmpresa;
    private String telefono;
    private String email;
    private String direccion;
    private List<Cuadrilla> cuadrillaAsignada;

}
