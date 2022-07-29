package co.proyectoGrado.repository.persistence;

import co.proyectoGrado.domain.excepciones.excepcion.ExcepcionDeProceso;
import co.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.repository.RetoRepository;
import co.proyectoGrado.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RetoRepositoryImpl implements RetoRepository {
    private final RetoCrud retoCrud;
    private final CursoCrud cursoCrud;
    private final String ACTIVO = "t";
    private final String INACTIVO = "f";
    private static final String NO_EXISTE_EL_RETO_CON_ID = "No existe el reto con id %s";
    private static final String ERRP_AL_ACTUALIZAR_EL_RETO_CON_ID = "Error al actualizar el reto con id %s";

    @Autowired
    public RetoRepositoryImpl(RetoCrud retoCrud, CursoCrud cursoCrud) {
        this.retoCrud = retoCrud;
        this.cursoCrud = cursoCrud;
    }

    @Override
    public List<Reto> getAll() {
        List<Reto> retos = new ArrayList<>();
        retoCrud.findAll().forEach(retoEntity -> {
            Reto reto = entityToDomain(retoEntity);
            if(reto.isEstado()==true){
                retos.add(reto);
            }
        });

        return retos;
    }

    @Override
    public List<Reto> getByIdCurso(int idCurso) {
        List<Reto> retos = new ArrayList<>();
        retoCrud.findByIdCursos(idCurso).forEach(retoEntity -> {
            Reto reto = entityToDomain(retoEntity);
            if(reto.isEstado()==true){
                retos.add(reto);
            }
        });

        return retos;
    }

    @Override
    public List<Reto> getPorIdCursoYTipo(int idCurso, String tipo) {
        List<Reto> retos = new ArrayList<>();
        retoCrud.findByIdCursosAndTipo(idCurso,tipo).forEach(retoEntity -> {
            Reto reto = entityToDomain(retoEntity);
            if(reto.isEstado()==true){
                retos.add(reto);
            }
        });
        return retos;
    }

    @Override
    public Reto get(String tipo) {
        RetoEntity retoEntity = retoCrud.findFirstByTipo(tipo);
        if(retoEntity!=null){
            if(retoEntity.getEstado() == ACTIVO){
            return entityToDomain(retoEntity);
            }else {
                return  null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Reto getById(Integer idReto) {
        RetoEntity retoEntity =  retoCrud.findFirstByIdReto(idReto);
        return entityToDomain(retoEntity);
    }

    @Override
    public Reto getTitulo(String titulo) {
        RetoEntity retoEntity = retoCrud.findFirstByTitulo(titulo);
        if(retoEntity!=null){
            return entityToDomain(retoEntity);
        }else{
            return null;
        }
    }

    @Override
    public Reto save(Reto reto) {
        try{
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(reto.getIdCurso());
            RetoEntity retoEntity = new RetoEntity();
            retoEntity.setIdReto(reto.getIdReto());
            retoEntity.setIdCursos(reto.getIdCurso());
            retoEntity.setTipo(reto.getTipo());
            retoEntity.setTitulo(reto.getTitulo());
            retoEntity.setDescripcion(reto.getDescripcion());
            retoEntity.setComentario(reto.getComentario());
            retoEntity.setEstado(reto.isEstado()? ACTIVO : INACTIVO);
            retoEntity.setCurso(cursoEntity);

            return entityToDomain(retoCrud.save(retoEntity));
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean actualizar(int id,Reto reto) {
        try{
            CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(reto.getIdCurso());
            RetoEntity retoEntity = new RetoEntity();
            retoEntity.setIdReto(reto.getIdReto());
            retoEntity.setIdCursos(reto.getIdCurso());
            retoEntity.setTipo(reto.getTipo());
            retoEntity.setTitulo(reto.getTitulo());
            retoEntity.setDescripcion(reto.getDescripcion());
            retoEntity.setComentario(reto.getComentario());
            retoEntity.setEstado(reto.isEstado()? ACTIVO : INACTIVO);
            retoEntity.setCurso(cursoEntity);
            return Boolean.TRUE;
        }catch (Exception e){
            e.printStackTrace();
            throw new ExcepcionDeProceso(String.format(ERRP_AL_ACTUALIZAR_EL_RETO_CON_ID,id));
        }
    }

    @Override
    public Boolean delete(int idReto) {
        if(retoCrud.findFirstByIdReto(idReto)!=null){
            RetoEntity retoEntity =  retoCrud.findFirstByIdReto(idReto);
           retoEntity.setEstado(INACTIVO);
            retoCrud.save(retoEntity);
            return true;
        }else{
           throw new ExcepcionDeProceso(String.format(NO_EXISTE_EL_RETO_CON_ID,idReto));
        }
    }

    private Reto entityToDomain(RetoEntity retoEntity){
        return new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                retoEntity.getTitulo(),retoEntity.getDescripcion(),
                retoEntity.getComentario(),ACTIVO.equals(retoEntity.getEstado()));
    }
}
