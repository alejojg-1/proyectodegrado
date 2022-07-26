package co.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaContenido {
    @JsonProperty
    private Integer idCategoriaContenido;
    @JsonProperty
    private Integer idPregunta;
    @JsonProperty
    private String nombre;
}
