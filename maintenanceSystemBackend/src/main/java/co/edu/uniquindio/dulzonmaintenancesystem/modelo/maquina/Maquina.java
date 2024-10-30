package co.edu.uniquindio.dulzonmaintenancesystem.modelo.maquina;

import co.edu.uniquindio.dulzonmaintenancesystem.Enums.EstadoMaquina;
import co.edu.uniquindio.dulzonmaintenancesystem.Enums.TipoMaquina;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document("Maquina")
public class Maquina {

    @Id
    private String idMaquina;
    private LocalDateTime fechaAdquisicion;
    private String descripcion;
    private LocalDateTime ultimoMantenimiento;
    private String ubicacion;
    private TipoMaquina tipoMaquina;
    private EstadoMaquina estadoMaquina;



}
