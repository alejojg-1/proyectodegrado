package co.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDocente {


    @JsonProperty
    private Integer idCursoDocente;
    @JsonProperty
    private int idDocente;
    @JsonProperty
    private int idCurso;
    @JsonProperty
    private boolean estado;

}
