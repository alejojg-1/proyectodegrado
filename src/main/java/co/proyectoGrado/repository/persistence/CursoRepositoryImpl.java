package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.Curso;
import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.repository.CursoRepository;
import co.proyectoGrado.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.repository.persistence.entity.RetoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoRepositoryImpl.class);

    private final CursoCrud cursoCrud;
    private static final String ACTIVO = "t";
    private static final String NO_EXISTE_EL_CURSO_CON_ID = "No existe el curso con el id: %s ";
    private static final String NO_EXISTE_EL_CURSO_CON_CODIGO = "No existe el curso con el codigo: %s ";
    private static final String NO_EXISTE_EL_CURSO_CON_NOMBRE = "No existe el curso con el nombre: %s ";
    private static final String NO_EXISTE_EL_CURSO_CON_GRADO = "No existe el curso con el grado: %s ";
    private static final String ERROR_ACTUALIZAR_EL_CURSO = "Error actualizando curso";
    private static final String ERROR_CREANDO_EL_CURSO = "Error creando curso";


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
    public Curso getById(int idCurso) {
        CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(idCurso);
        if(cursoEntity != null){
            Curso curso = new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));
            return curso;
        }else{
            throw new RuntimeException(
                    String.format( NO_EXISTE_EL_CURSO_CON_ID,idCurso));
        }
    }

    @Override
    public Curso getByCodigo(String codigo) {
        CursoEntity cursoEntity = cursoCrud.findFirstByCodigo(codigo);
        if(cursoEntity != null){
            Curso curso = new Curso(cursoEntity.getIdCursos(),
                    cursoEntity.getGrado(),
                    cursoEntity.getNombre(),
                    cursoEntity.getCodigo(),
                    mapperReto(cursoEntity.getReto()));
            return curso;
        }else{
            throw new ExcepcionDeProceso(
                    String.format( NO_EXISTE_EL_CURSO_CON_CODIGO,codigo));
        }
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
            throw new RuntimeException(
                    String.format( NO_EXISTE_EL_CURSO_CON_GRADO,grado));
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
            throw new ExcepcionDeProceso(
                    String.format( NO_EXISTE_EL_CURSO_CON_NOMBRE,nombre));
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
            LOGGER.error(ERROR_CREANDO_EL_CURSO,e);
            throw new ExcepcionDeProceso(ERROR_CREANDO_EL_CURSO);
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
                LOGGER.error(ERROR_ACTUALIZAR_EL_CURSO,e);
                throw new ExcepcionDeProceso(ERROR_ACTUALIZAR_EL_CURSO);
            }
        } else {
            return false;
        }
        return null;
    }

    @Override
    public Boolean delete(int idCurso) {
        if (cursoCrud.findFirstByIdCursos(idCurso) != null) {
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(idCurso);
            cursoCrud.delete(cursoEntity);
            return true;
        } else {
            return false;
        }
    }

    private List<Reto> mapperReto(List<RetoEntity> listaRetoEntity) {
        return listaRetoEntity.stream().map(retoEntity ->
                new Reto(retoEntity.getIdReto(), retoEntity.getIdCursos(), retoEntity.getTipo(),
                        retoEntity.getTitulo(), retoEntity.getDescripcion(), retoEntity.getComentario(),
                        ACTIVO.equals(retoEntity.getEstado()))).collect(Collectors.toList());
    }

    private Curso entityToDomain(CursoEntity cursoEntity) {
        return new Curso(cursoEntity.getIdCursos(),
                cursoEntity.getGrado(), cursoEntity.getNombre(), null,
                cursoEntity.getReto() != null ? mapperReto(cursoEntity.getReto()) :
                        new ArrayList<>());
    }
}
