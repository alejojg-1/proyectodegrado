package co.proyectoGrado.domain.service.cursodocente;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.CursoDocente;
import co.proyectoGrado.repository.CursoDocenteRepository;

import co.proyectoGrado.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.repository.persistence.crud.CursoDocenteCrud;
import co.proyectoGrado.repository.persistence.crud.DocenteCrud;
import co.proyectoGrado.repository.persistence.entity.CursoDocenteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoDocenteService {

    private final CursoDocenteRepository cursoDocenteRepository;
    @Autowired
    private CursoDocenteCrud cursoDocenteCrud;

    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    public CursoDocenteService(CursoDocenteRepository cursoDocenteRepository) {
        this.cursoDocenteRepository = cursoDocenteRepository;
    }

    public CursoDocente get(int idcursos ) {
        return cursoDocenteRepository.getIdDocente(idcursos);
    }

    public boolean save(CursoDocente cursoDocente) {
        try {
            cursoDocenteRepository.save(cursoDocente);
            return Boolean.TRUE;
        } catch (ExcepcionDeProceso e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    public Boolean actualizar(int id, CursoDocente cursodocente) {
        CursoDocenteEntity contenido =  cursoDocenteCrud.findById_IdCursoDocente(id);
        if (contenido != null) {
            CursoDocenteEntity contenidoMapper = mapper.map(cursodocente, CursoDocenteEntity.class);
            contenidoMapper.getId().setIdCursoDocente(contenido.getId().getIdCursoDocente());
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
