package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JuegoPregunta {
    @JsonProperty
    private Integer idJuegoPreguntas;
    @JsonProperty
    private int idPreguntas;
    @JsonProperty
    private int idReto;
    @JsonProperty
    private boolean estado;
}
