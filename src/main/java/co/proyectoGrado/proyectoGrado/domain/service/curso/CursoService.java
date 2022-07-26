package co.proyectoGrado.proyectoGrado.domain.service.curso;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private  final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso get(String nombre) {
        return cursoRepository.getByGrado(nombre);
    }

    public Curso getByCodigo(String codigo) {
        return cursoRepository.getByCodigo(codigo);
    }

    public List<Curso> getAll(){
        return cursoRepository.getAll();
    }

    public Curso getById(int idCurso) {
        return cursoRepository.getById(idCurso);
    }

    public Curso save(Curso curso) {
        try {
            return cursoRepository.save(curso);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Boolean actualizar(int id, Curso curso) {
        return  cursoRepository.actualizar(id, curso);
    }

    public Boolean eliminar(int id) {
        return cursoRepository.delete(id);
    }
}
