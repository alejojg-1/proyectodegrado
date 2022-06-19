package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaContenido {
    @JsonProperty
    private int idCategoriaContenido;
    @JsonProperty
    private Integer idPregunta;
    @JsonProperty
    private String nombre;
}
