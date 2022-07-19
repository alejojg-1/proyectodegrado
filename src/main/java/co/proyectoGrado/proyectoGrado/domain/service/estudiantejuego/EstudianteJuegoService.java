package co.proyectoGrado.proyectoGrado.domain.service.estudiantejuego;

import co.proyectoGrado.proyectoGrado.domain.model.EstudianteJuego;
import co.proyectoGrado.proyectoGrado.domain.repository.EstudianteJuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteJuegoService {

    private final EstudianteJuegoRepository estudianteJuegoRepository;

    @Autowired
    public EstudianteJuegoService(EstudianteJuegoRepository estudianteJuegoRepository) {
        this.estudianteJuegoRepository = estudianteJuegoRepository;
    }

    public List<EstudianteJuego> getAll(){
        return estudianteJuegoRepository.getAll();
    }

    public EstudianteJuego get(int idReto) {
        return estudianteJuegoRepository.getByIdReto(idReto);
    }

    public List<EstudianteJuego> obtenerPorIdReto( Integer idReto){
        return  estudianteJuegoRepository.obtenerListaPorIdReto(idReto);
    }

    public EstudianteJuego save(EstudianteJuego estudianteJuego) {
        try {
            return estudianteJuegoRepository.save(estudianteJuego);
        } catch (RuntimeException e) {
           throw  new RuntimeException(e);
        }
    }

    public Boolean actualizar(int id, EstudianteJuego estudianteJuego) {
        return  estudianteJuegoRepository.actualizar(id, estudianteJuego);
    }

    public Boolean eliminar(int id) {

        return estudianteJuegoRepository.delete(id);
    }
}
