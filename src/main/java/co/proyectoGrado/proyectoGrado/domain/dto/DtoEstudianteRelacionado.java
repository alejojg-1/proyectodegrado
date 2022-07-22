package co.proyectoGrado.proyectoGrado.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoEstudianteRelacionado {

    @JsonProperty
    private Integer idCursoEstudiante;
    @JsonProperty
    private Integer idEstudiante;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellido;
    @JsonProperty
    private int identificacion;
    @JsonProperty
    private String correo;
}
