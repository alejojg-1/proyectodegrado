package co.proyectoGrado.domain.service.reto;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.repository.RetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RetoService {
    private final RetoRepository retoRepository;

    @Autowired
    public RetoService(RetoRepository retoRepository) {
        this.retoRepository = retoRepository;
    }
    public List<Reto> getAll(){
        return retoRepository.getAll();
    }

    public List<Reto> getByCursoId(int idCurso){
        return retoRepository.getByIdCurso(idCurso);
    }

    public List<Reto> getPorCursoIdYTipo(int idCurso, String tipo){
        return retoRepository.getPorIdCursoYTipo(idCurso,tipo);
    }

    public Reto get(String tipo) {
        return retoRepository.get(tipo);
    }

    public Reto getById(Integer idReto) {
        return retoRepository.getById(idReto);
    }

    public Reto save(Reto reto) {
        try {
           return retoRepository.save(reto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e) ;
        }
    }

    public Boolean actualizar(int id, Reto reto) {
        try{
            return  retoRepository.actualizar(id, reto);
        }catch (ExcepcionDeProceso excepcionDeProceso){
            excepcionDeProceso.getMessage();
            excepcionDeProceso.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean eliminar(int id) {
        try{
            return retoRepository.delete(id);
        }catch (ExcepcionDeProceso excepcionDeProceso){
            excepcionDeProceso.getMessage();
            excepcionDeProceso.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
