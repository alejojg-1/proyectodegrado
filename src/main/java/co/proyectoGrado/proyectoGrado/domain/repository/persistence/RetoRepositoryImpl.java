package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.repository.RetoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RetoRepositoryImpl implements RetoRepository {
    private final RetoCrud retoCrud;
    private final CursoCrud cursoCrud;
    private final String ACTIVO = "S"; //Validar cuál usar

    @Autowired
    public RetoRepositoryImpl(RetoCrud retoCrud, CursoCrud cursoCrud) {
        this.retoCrud = retoCrud;
        this.cursoCrud = cursoCrud;
    }

    @Override
    public List<Reto> getAll() {
        List<Reto> retos = new ArrayList<>();
        retoCrud.findAll().forEach(retoEntity -> {
            Reto reto = new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                    retoEntity.getTitulo(),retoEntity.getDescripcion(),
                    retoEntity.getComentario(),ACTIVO.equals(retoEntity.getEstado()));
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
            Reto reto = new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                    retoEntity.getTitulo(),retoEntity.getDescripcion(),
                    retoEntity.getComentario(),ACTIVO.equals(retoEntity.getEstado()));
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
            Reto reto = new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                    retoEntity.getTitulo(),retoEntity.getDescripcion(),
                    retoEntity.getComentario(),ACTIVO.equals(retoEntity.getEstado()));
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
            if(retoEntity.getEstado() == "t"){
            return new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                    retoEntity.getTitulo(),retoEntity.getDescripcion(),
                    retoEntity.getComentario(),"t".equals(retoEntity.getEstado()));
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
        return new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                retoEntity.getTitulo(),retoEntity.getDescripcion(),
                retoEntity.getComentario(),ACTIVO.equals(retoEntity.getEstado()));
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
            retoEntity.setEstado(reto.isEstado()? String.valueOf('S') : String.valueOf('f'));
            retoEntity.setCurso(cursoEntity);

            return entityToDomain(retoCrud.save(retoEntity));
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    private Reto entityToDomain(RetoEntity retoEntity){
        return new Reto(retoEntity.getIdReto(),retoEntity.getIdCursos(),retoEntity.getTipo(),
                retoEntity.getTitulo(),retoEntity.getDescripcion(),
                retoEntity.getComentario(),"t".equals(retoEntity.getEstado()));
    }


    @Override
    public Boolean actualizar(int id,Reto reto) {
        try{
            RetoEntity retoEntity = new RetoEntity();
            retoEntity.setIdReto(reto.getIdReto());
            retoEntity.setTipo(reto.getTipo());
            retoEntity.setTitulo(reto.getTitulo());
            retoEntity.setDescripcion(reto.getDescripcion());
            retoEntity.setComentario(reto.getComentario());
            retoEntity.setEstado(reto.isEstado()? String.valueOf('t') : String.valueOf('f'));
            retoCrud.save(retoEntity);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }




    @Override
    public Boolean delete(int idReto) {
        if(retoCrud.findFirstByIdReto(idReto)!=null){
            RetoEntity retoEntity =  retoCrud.findFirstByIdReto(idReto);
           retoEntity.setEstado("f");
            retoCrud.save(retoEntity);
            return true;
        }else{
            return false;
        }
    }
}
