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
    private Integer idEstudianteJuego;
    @JsonProperty
    private Double calificacion;
    @JsonProperty
    private int idReto;
    @JsonProperty
    private int idEstudiantes;

    public EstudianteJuego(double calificacion,int idReto,int idEstudiantes){
        this.calificacion = calificacion;
        this.idReto = idReto;
        this.idEstudiantes= idEstudiantes;
    }
}
