package co.proyectoGrado.proyectoGrado.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRelacionEstudianteCurso {
    private String correoEstudiante;
    private String codigoCurso;
}
