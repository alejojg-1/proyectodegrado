package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoContenido {

    @JsonProperty
    private Long idCursoContenido;
    @JsonProperty
    private int idCategoriaContenido;
    @JsonProperty
    private int idCurso;
    @JsonProperty
    private String comentario;
    @JsonProperty
    private String descripcion;
    @JsonProperty
    private String imagen;
    @JsonProperty
    private String video;

}
