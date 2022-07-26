package co.proyectoGrado.domain.dto;

import co.proyectoGrado.domain.model.Pregunta;
import co.proyectoGrado.domain.model.Reto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCreacionReto {
    private Reto reto;
    private List<Pregunta> listaPreguntas;
}
