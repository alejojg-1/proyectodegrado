package co.proyectoGrado.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCreacionCurso {
    private String correoDocente;
    private Integer idCursos;
    private String grado;
    private String nombre;
}
