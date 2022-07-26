package co.proyectoGrado.domain.service.reto;

import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.repository.RetoRepository;
import co.proyectoGrado.repository.persistence.crud.RetoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RetoService {
    private final RetoRepository retoRepository;
    @Autowired
    private RetoCrud retoCrud;

    @Autowired
    public RetoService(RetoRepository retoRepository) {
        this.retoRepository = retoRepository;
    }
   // private final ModelMapper mapper = new ModelMapper();

    public List<Reto> getAll(){
        return retoRepository.getAll();
    }
    public List<Reto> getByCursoId(int idCurso){
        return retoRepository.getByIdCurso(idCurso);
    }
    public List<Reto> getPorCursoIdYTipo(int idCurso, String tipo){
        return retoRepository.getPorIdCursoYTipo(idCurso,tipo);
    }
    public Reto get(String titulo) {
        return retoRepository.get(titulo);
    }

    public Reto getById(Integer idReto) {
        return retoRepository.getById(idReto);
    }

    public Reto save(Reto reto) {
        try {
           return retoRepository.save(reto);
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        }
    }

    public Boolean actualizar(int id, Reto reto) {
        return  retoRepository.actualizar(id, reto);
    }
    public Boolean eliminar(int id) {
        return retoRepository.delete(id);
    }
}
