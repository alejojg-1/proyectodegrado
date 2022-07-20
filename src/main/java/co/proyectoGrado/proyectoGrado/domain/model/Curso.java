package co.proyectoGrado.proyectoGrado.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @JsonProperty
    private Integer idCursos;
    @JsonProperty
    private String grado;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String codigo;
    @JsonProperty
    private List<Reto> listaRetos;
}
