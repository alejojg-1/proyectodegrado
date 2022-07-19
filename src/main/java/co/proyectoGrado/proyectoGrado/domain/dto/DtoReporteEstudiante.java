package co.proyectoGrado.proyectoGrado.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoReporteEstudiante {
    private Integer idReto;
    private String tituloReto;
    private Integer identificacion;
    private String nombre;
    private String correo;
    private Double calificacion;
}
