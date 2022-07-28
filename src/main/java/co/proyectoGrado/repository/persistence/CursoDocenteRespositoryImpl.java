package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionValorInvalido;
import co.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.repository.CursoDocenteRepository;
import co.proyectoGrado.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.repository.persistence.crud.CursoDocenteCrud;
import co.proyectoGrado.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.repository.persistence.entity.CursoDocenteEntity;
import co.proyectoGrado.repository.persistence.entity.CursoDocentePK;
import co.proyectoGrado.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.repository.persistence.entity.DocenteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDocenteRespositoryImpl implements CursoDocenteRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstudianteRepositoryImpl.class);

    private static final String ACTIVO ="t";
    private static final String INACTIVO ="f";
    private static final String ERROR_ACTUALIZAR_EL_CURSO_DOCENTE = "Error actualizando curso docente";
    private static final String ERROR_CREANDO_EL_CURSO_DOCENTE = "Error creando curso docente";


    private final CursoDocenteCrud cursoDocenteCrud;
    private final CursoCrud cursoCrud;
    private final DocenteCrud docenteCrud;

    @Autowired
    public CursoDocenteRespositoryImpl(CursoDocenteCrud cursoDocenteCrud,
                                       CursoCrud cursoCrud, DocenteCrud docenteCrud) {
        this.cursoDocenteCrud = cursoDocenteCrud;
        this.cursoCrud = cursoCrud;
        this.docenteCrud = docenteCrud;
    }

    @Override
    public List<CursoDocente> getAll() {
        List<CursoDocente> cursoDocentes = new ArrayList<>();

        cursoDocenteCrud.findAll().forEach(cursoDocenteEntity -> {
            CursoDocente cursoDocente = new CursoDocente(cursoDocenteEntity.getId().getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    ACTIVO.equals(cursoDocenteEntity.getEstado()));

            if (cursoDocente.isEstado() == true) {
                cursoDocentes.add(cursoDocente);
            }

        });

        return cursoDocentes;
    }

    @Override
    public CursoDocente get(int idCursoDocente) {

        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstById_IdCursoDocente(idCursoDocente);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getId().getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    ACTIVO.equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public CursoDocente getIdDocente(int idDocente) {

        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findById_IdCursoDocente(idDocente);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getId().getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    ACTIVO.equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public List<CursoDocente> getByIdDocente(int idDocente) {
        List<CursoDocente> listaCursoDocente = new ArrayList<>();
        List<CursoDocenteEntity> listaCursoDocenteEntity = cursoDocenteCrud.findById_IdDocentes(idDocente);
        listaCursoDocenteEntity.forEach(cursoDocenteEntity -> {
            CursoDocente cursoDocente = new CursoDocente(cursoDocenteEntity.getId().getIdCursoDocente()
                    , cursoDocenteEntity.getId().getIdDocentes()
                    , cursoDocenteEntity.getId().getIdCursos(), ACTIVO.equals(cursoDocenteEntity.getEstado()));
            listaCursoDocente.add(cursoDocente);
        });
        return listaCursoDocente;
    }

    @Override
    public CursoDocente getIdCursos(int idCursos) {
        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstByCurso_IdCursos(idCursos);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getId().getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    ACTIVO.equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public Boolean save(CursoDocente cursoDocente) {

        try {
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoDocente.getIdCurso());
            DocenteEntity docenteEntity = docenteCrud.findFirstByIdDocentes(cursoDocente.getIdDocente());

            if (cursoEntity == null || docenteEntity == null) {
                throw new ExcepcionValorInvalido("error obteniendo las entidades para Curso Docente");

            }
            CursoDocenteEntity cursoDocenteEntity = new CursoDocenteEntity();
            cursoDocenteEntity.setId(new CursoDocentePK());
            cursoDocenteEntity.getId().setIdDocentes(cursoDocente.getIdDocente());
            cursoDocenteEntity.getId().setIdCursos(cursoDocente.getIdCurso());
            cursoDocenteEntity.setEstado(cursoDocente.isEstado() ? ACTIVO : INACTIVO);
            cursoDocenteEntity.setDocente(docenteEntity);
            cursoDocenteEntity.setCurso(cursoEntity);
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;

        } catch (Exception e) {
            LOGGER.error(ERROR_CREANDO_EL_CURSO_DOCENTE,e);
            throw new ExcepcionDeProceso(ERROR_CREANDO_EL_CURSO_DOCENTE);
        }
    }

    @Override
    public Boolean actualizar(int id, CursoDocente cursoDocente) {

        try {
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoDocente.getIdCurso());
            DocenteEntity docenteEntity = docenteCrud.findFirstByIdDocentes(cursoDocente.getIdDocente());

            if (cursoEntity == null || docenteEntity == null) {
                throw new ExcepcionValorInvalido("error obteniendo las entidades para Curso Docente");

            }
            CursoDocenteEntity cursoDocenteEntity = new CursoDocenteEntity();
            cursoDocenteEntity.setId(new CursoDocentePK());
            cursoDocenteEntity.getId().setIdDocentes(cursoDocente.getIdDocente());
            cursoDocenteEntity.getId().setIdCursos(cursoDocente.getIdCurso());
            cursoDocenteEntity.setEstado(cursoDocente.isEstado() ? ACTIVO : INACTIVO);
            cursoDocenteEntity.setDocente(docenteEntity);
            cursoDocenteEntity.setCurso(cursoEntity);
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;
        } catch (Exception e) {
            LOGGER.error(ERROR_ACTUALIZAR_EL_CURSO_DOCENTE,e);
            throw new ExcepcionDeProceso(ERROR_ACTUALIZAR_EL_CURSO_DOCENTE);
        }
    }

    @Override
    public Boolean delete(int idCursoDocente) {
        if (cursoDocenteCrud.findById_IdCursoDocente(idCursoDocente) != null) {
            CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstById_IdCursoDocente(idCursoDocente);
            cursoDocenteEntity.setEstado(INACTIVO);
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;
        } else {
            return false;
        }
    }
}


