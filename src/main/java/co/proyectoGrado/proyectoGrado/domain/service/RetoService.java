package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.repository.RetoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.modelmapper.ModelMapper;
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

    public boolean save(Reto reto) {

        try {
            retoRepository.save(reto);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, Reto reto) {
        return  retoRepository.actualizar(id, reto);
    }
    public Boolean eliminar(int id) {
        return retoRepository.delete(id);
    }
}
