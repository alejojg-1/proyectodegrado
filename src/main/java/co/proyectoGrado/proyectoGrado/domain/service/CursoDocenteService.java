package co.proyectoGrado.proyectoGrado.domain.service;

import co.proyectoGrado.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.proyectoGrado.domain.model.Estudiante;
import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoDocenteRepository;

import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoDocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoDocenteEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.DocenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoDocenteService {

    private final CursoDocenteRepository cursoDocenteRepository;
    @Autowired
    private CursoDocenteCrud cursoDocenteCrud;

    @Autowired
    private CursoCrud cursoCrud;

    @Autowired
    private DocenteCrud docenteCrud;

    @Autowired
    public CursoDocenteService(CursoDocenteRepository cursoDocenteRepository) {
        this.cursoDocenteRepository = cursoDocenteRepository;
    }


    private final ModelMapper mapper = new ModelMapper();


    public CursoDocente get(int idcursos ) {

        return cursoDocenteRepository.getIdDocente(idcursos);}

    public boolean save(CursoDocente cursoDocente) {
       // CursoEntity cursoEntity = cursoCrud.findById(cursoDocente.getIdCursos()).orElse(null);
       // DocenteEntity docenteEntity = docenteCrud.findById(cursoDocente.getIdDocente()).orElse(null);
       // CursoDocenteEntity contenido = new CursoDocenteEntity(cursoDocente.getIdCursoDocente(),docenteEntity,cursoEntity);
        try {
            cursoDocenteRepository.save(cursoDocente);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, CursoDocente cursodocente) {
        CursoDocenteEntity contenido =  cursoDocenteCrud.findByIdCursoDocente(id);
        if ("".equals(contenido.getIdCursoDocente())) {
            CursoDocenteEntity contenidoMapper = mapper.map(cursodocente, CursoDocenteEntity.class);
            contenidoMapper.setIdCursoDocente(contenido.getIdCursoDocente());
            cursoDocenteCrud.save(contenidoMapper);
        }
        return  cursoDocenteRepository.actualizar(id, cursodocente);
    }

    public Boolean eliminar(int id) {
        return cursoDocenteRepository.delete(id);
    }

    public List<CursoDocente> getAll(){
        return cursoDocenteRepository.getAll();
    }
}
