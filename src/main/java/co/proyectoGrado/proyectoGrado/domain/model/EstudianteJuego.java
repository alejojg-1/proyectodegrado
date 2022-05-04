package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EstudianteJuego {
    @JsonProperty
    private int idEstudianteJuego;
    @JsonProperty
    private double calificacion;
    @JsonProperty
    private int idReto;
    @JsonProperty
    private int idEstudiantes;
    @JsonProperty
    private int idEstudianteJuegoRespuesta;
}
