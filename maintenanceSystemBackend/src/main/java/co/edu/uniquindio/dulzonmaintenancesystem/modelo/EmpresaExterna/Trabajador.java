package co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Trabajador {

    private int idTrabajador;
    private String nombre;
    private String especialidad;
    private String idEmpresa;

}
