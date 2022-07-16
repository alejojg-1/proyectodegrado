package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pregunta {
    @JsonProperty
    private Integer idPregunta;
    @JsonProperty
    private String texto;
    @JsonProperty
    private String imagen;
    @JsonProperty
    private String respuesta;
    @JsonProperty
    private String opcion1;
    @JsonProperty
    private String opcion2;
    @JsonProperty
    private String opcion3;
    @JsonProperty
    private String opcion4;
    @JsonProperty
    private boolean estado;

}
