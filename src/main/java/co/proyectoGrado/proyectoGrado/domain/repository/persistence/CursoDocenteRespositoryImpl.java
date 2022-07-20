package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoDocenteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoDocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoDocenteEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoDocentePK;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.DocenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDocenteRespositoryImpl implements CursoDocenteRepository {

    private static final String ACTIVO ="t";
    private static final String INACTIVO ="f";
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
                    "S".equals(cursoDocenteEntity.getEstado()));

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
                    "S".equals(cursoDocenteEntity.getEstado()));
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
                    "S".equals(cursoDocenteEntity.getEstado()));
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
                    , cursoDocenteEntity.getId().getIdCursos(), "S".equals(cursoDocenteEntity.getEstado()));
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
                    "S".equals(cursoDocenteEntity.getEstado()));
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
                throw new Exception("error obteniendo las entidades para Curso Docente");

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
            e.printStackTrace();
            throw new RuntimeException("Error creando Curso Docente");
        }
    }

    @Override
    public Boolean actualizar(int id, CursoDocente cursodocente) {

        try {
            CursoDocenteEntity cursoDocenteEntity = new CursoDocenteEntity();
            cursoDocenteEntity.getId().setIdCursoDocente(cursodocente.getIdCursoDocente());
            cursoDocenteEntity.getCurso().setIdCursos(cursodocente.getIdCurso());
            cursoDocenteEntity.getDocente().setIdDocentes(cursodocente.getIdCursoDocente());
            cursoDocenteEntity.setEstado(cursodocente.isEstado() ? String.valueOf('t') : String.valueOf('f'));
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(int idCursoDocente) {
        if (cursoDocenteCrud.findById_IdCursoDocente(idCursoDocente) != null) {
            CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstById_IdCursoDocente(idCursoDocente);
            cursoDocenteEntity.setEstado("f");
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;
        } else {
            return false;
        }
    }
}


