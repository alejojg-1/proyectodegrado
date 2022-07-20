package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    private final CursoCrud cursoCrud;

    @Autowired
    public CursoRepositoryImpl(CursoCrud cursoCrud) {
        this.cursoCrud = cursoCrud;
    }

    @Override
    public List<Curso> getAll() {
        List<Curso> cursos = new ArrayList<>();
        cursoCrud.findAll().forEach(cursoEntity -> {
            Curso curso = new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));

            cursos.add(curso);
        });
        return cursos;
    }

    @Override
    public List<Curso> getByIdsCursos(List<Integer> listaIdsCursos) {
        List<Curso> cursos = new ArrayList<>();
        cursoCrud.findByIdCursosIn(listaIdsCursos).forEach(cursoEntity -> {
            Curso curso = new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));
            cursos.add(curso);
        });
        return cursos;
    }

    @Override
    public Curso getByGrado(String grado) {
        CursoEntity cursoEntity = cursoCrud.findFirstByGrado(grado);
        if (cursoEntity != null) {
            return new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));
        } else {
            return null;
        }
    }

    @Override
    public Curso getByNombre(String nombre) {
        CursoEntity cursoEntity = cursoCrud.findFirstByNombre(nombre);
        if (cursoEntity != null) {
            return new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));
        } else {
            return null;
        }
    }

    @Override
    public Curso save(Curso curso) {
        try {
            CursoEntity cursoEntity = new CursoEntity();
            cursoEntity.setGrado(curso.getGrado());
            cursoEntity.setNombre(curso.getNombre());
            cursoEntity.setCodigo(curso.getCodigo());
            return entityToDomain(cursoCrud.save(cursoEntity));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean actualizar(int id, Curso curso) {
        if (cursoCrud.findById(id) != null) {
            try {
                CursoEntity cursoEntity = new CursoEntity();
                cursoEntity.setIdCursos(curso.getIdCursos());
                cursoEntity.setGrado(curso.getGrado());
                cursoEntity.setNombre(curso.getNombre());

                cursoCrud.save(cursoEntity);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
        return null;
    }

    @Override
    public Boolean delete(int idCurso) {
        if (cursoCrud.findByIdCursos(idCurso) != null) {
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(idCurso);
            cursoCrud.delete(cursoEntity);
            return true;
        } else {
            return false;
        }
    }

    private List<Reto> mapperReto(List<RetoEntity> listaRetoEntity) {
        return listaRetoEntity.stream().map(retoEntity ->
                new Reto(retoEntity.getIdReto(), retoEntity.getIdCursos(), retoEntity.getTipo(), retoEntity.getTitulo(),
                        retoEntity.getDescripcion(), retoEntity.getComentario(),
                        "S".equals(retoEntity.getEstado()))).collect(Collectors.toList());
    }

    private Curso entityToDomain(CursoEntity cursoEntity) {
        return new Curso(cursoEntity.getIdCursos(),
                cursoEntity.getGrado(), cursoEntity.getNombre(), null,
                cursoEntity.getReto() != null ? mapperReto(cursoEntity.getReto()) :
                        new ArrayList<>());
    }
}
