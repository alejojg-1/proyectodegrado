package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reto {
    @JsonProperty
    private Integer idReto;
    @JsonProperty
    private Integer idCurso;
    @JsonProperty
    private String tipo;
    @JsonProperty
    private String titulo;
    @JsonProperty
    private String descripcion;
    @JsonProperty
    private String comentario;
    @JsonProperty
    private boolean estado;

}
