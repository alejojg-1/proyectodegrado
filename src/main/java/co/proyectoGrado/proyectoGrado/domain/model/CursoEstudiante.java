package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CursoEstudiante {
    @JsonProperty
    private Integer idCursoEstudiante;
    @JsonProperty
    private int idEstudiantes;
    @JsonProperty
    private int idCursos;

}
