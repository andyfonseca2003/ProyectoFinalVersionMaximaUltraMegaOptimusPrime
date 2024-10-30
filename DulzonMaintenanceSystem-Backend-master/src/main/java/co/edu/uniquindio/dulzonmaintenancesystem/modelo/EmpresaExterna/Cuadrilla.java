package co.edu.uniquindio.dulzonmaintenancesystem.modelo.EmpresaExterna;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cuadrilla {

    private String idCuadrilla;
    private String nombre;
    private List<Trabajador> trabajadores;

}
