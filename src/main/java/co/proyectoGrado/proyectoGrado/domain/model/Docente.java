package co.proyectoGrado.proyectoGrado.domain.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Docente {
    @JsonProperty
    private Integer idDocente;
    @JsonProperty
    private String nombre;
    @JsonProperty
    private String apellido;
    @JsonProperty
    private int identificacion;
    @JsonProperty
    private String correo;
    @JsonProperty
    private String contrasena;
    @JsonProperty
    private boolean estado;
}
