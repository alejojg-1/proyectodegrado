package co.proyectoGrado.proyectoGrado.domain.dto;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuegoRespuesta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoRespuestasReto {
    private String correoEstudiante;
    private List<EstudianteJuegoRespuesta> listaEstudianteJuegoRespuestas;
}
