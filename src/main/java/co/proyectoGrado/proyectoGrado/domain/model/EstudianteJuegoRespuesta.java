package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EstudianteJuegoRespuesta {

    @JsonProperty
    private Integer idEstudianteJuegoRespuestas;
    @JsonProperty
    private int idjuegoPreguntas;
    @JsonProperty
    private int idPreguntas;
    @JsonProperty
    private int idReto;
    @JsonProperty
    private String respuesta;
    @JsonProperty
    private boolean estado;

}
