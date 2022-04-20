package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDocente {


    @JsonProperty
    private int idCursoDocente;
    @JsonProperty
    private int idDocente;
    @JsonProperty
    private int idCursos;
    @JsonProperty
    private boolean estado;
}
