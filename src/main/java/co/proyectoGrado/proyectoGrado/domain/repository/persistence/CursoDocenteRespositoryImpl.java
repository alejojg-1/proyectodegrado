package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoDocenteRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoDocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoDocenteEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.DocenteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoDocenteRespositoryImpl implements CursoDocenteRepository {

    private final CursoDocenteCrud cursoDocenteCrud;


    private final CursoCrud cursoCrud;
    private final DocenteCrud docenteCrud;

    @Autowired
    public CursoDocenteRespositoryImpl(CursoDocenteCrud cursoDocenteCrud,CursoCrud cursoCrud,DocenteCrud docenteCrud) {
        this.cursoDocenteCrud = cursoDocenteCrud;
        this.cursoCrud = cursoCrud;
        this.docenteCrud = docenteCrud;
    }

    @Override
    public List<CursoDocente> getAll() {
        List<CursoDocente> cursoDocentes = new ArrayList<>();

        cursoDocenteCrud.findAll().forEach(cursoDocenteEntity -> {
            CursoDocente cursoDocente = new CursoDocente(cursoDocenteEntity.getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    "S".equals(cursoDocenteEntity.getEstado()));

            if(cursoDocente.isEstado()==true){
                cursoDocentes.add(cursoDocente);
            }

        });

        return cursoDocentes;
    }

    @Override
    public CursoDocente get(int idCursoDocente) {

        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstByIdCursoDocente(idCursoDocente);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    "S".equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public CursoDocente getIdDocente(int idDocente) {

        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findByIdCursoDocente(idDocente);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    "S".equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }


    @Override
    public CursoDocente getIdCursos(int idCursos) {
        CursoDocenteEntity cursoDocenteEntity = cursoDocenteCrud.findFirstByCurso_IdCursos(idCursos);
        if (cursoDocenteEntity != null) {
            return new CursoDocente(cursoDocenteEntity.getIdCursoDocente(),
                    cursoDocenteEntity.getDocente().getIdDocentes(), cursoDocenteEntity.getCurso().getIdCursos(),
                    "S".equals(cursoDocenteEntity.getEstado()));
        } else {
            return null;
        }
    }

    @Override
    public Boolean save(CursoDocente cursoDocente) {

        try {
            CursoEntity cursoEntity = cursoCrud.findById(cursoDocente.getIdCursos()).orElse(null);
            DocenteEntity docenteEntity = docenteCrud.findById(cursoDocente.getIdDocente()).orElse(null);

            //TO DO
           /* if (cursoEntity  ==null|| docenteEntity==null){
                throw new Exception("error obteniendo las entidades para Curso Docente");

            }*/

            CursoDocenteEntity cursoDocenteEntity = new CursoDocenteEntity(cursoDocente.getIdCursoDocente(),docenteEntity,cursoEntity);
            cursoDocenteEntity.setIdCursoDocente(cursoDocente.getIdCursoDocente());
            cursoDocenteEntity.setEstado(cursoDocente.isEstado() ? String.valueOf('t') : String.valueOf('f'));
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
@Override
    public Boolean actualizar(int id, CursoDocente cursodocente) {

        try {
            CursoDocenteEntity cursoDocenteEntity = new CursoDocenteEntity();
            cursoDocenteEntity.setIdCursoDocente(cursodocente.getIdCursoDocente());
            cursoDocenteEntity.getCurso().setIdCursos(cursodocente.getIdCursos());
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
        if(cursoDocenteCrud.findByIdCursoDocente(idCursoDocente)!=null){
            CursoDocenteEntity cursoDocenteEntity =  cursoDocenteCrud.findFirstByIdCursoDocente(idCursoDocente);
            cursoDocenteEntity.setEstado("f");
            cursoDocenteCrud.save(cursoDocenteEntity);
            return true;
        }else{
            return false;
        }
    }
}


