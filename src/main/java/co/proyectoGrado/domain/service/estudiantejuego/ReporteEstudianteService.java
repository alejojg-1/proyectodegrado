package co.proyectoGrado.domain.service.estudiantejuego;

import co.proyectoGrado.domain.dto.DtoReporteEstudiante;
import co.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.domain.service.estudiante.EstudianteService;
import co.proyectoGrado.domain.service.reto.RetoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteEstudianteService {

    private final EstudianteJuegoService estudianteJuegoService;
    private final EstudianteService estudianteService;
    private final RetoService retoService;


    public ReporteEstudianteService(EstudianteJuegoService estudianteJuegoService,
                                    EstudianteService estudianteService,
                                    RetoService retoService) {
        this.estudianteJuegoService = estudianteJuegoService;
        this.estudianteService = estudianteService;
        this.retoService = retoService;
    }

    public List<DtoReporteEstudiante> ejecutar(Integer idReto){
        List<DtoReporteEstudiante> listaReporteEstudiantesPorReto= new ArrayList<>();
        estudianteJuegoService.obtenerPorIdReto(idReto).forEach(estudianteJuego -> {
            Reto reto = retoService.getById(estudianteJuego.getIdReto());
            Estudiante estudiante = estudianteService.getById(estudianteJuego.getIdEstudiantes());
            listaReporteEstudiantesPorReto.add(new DtoReporteEstudiante(
                    reto.getIdReto(),reto.getTitulo(),estudiante.getIdentificacion(),
                    estudiante.getNombre(),estudiante.getCorreo(),estudianteJuego.getCalificacion()
            ));
        });
      return listaReporteEstudiantesPorReto;
    }

}
