package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.Reto;
import co.proyectoGrado.proyectoGrado.domain.repository.RetoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.RetoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.RetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RetoRepositoryImpl implements RetoRepository {
    private final RetoCrud retoCrud;
    private final String ACTIVO = "t";

    @Autowired
    public RetoRepositoryImpl(RetoCrud retoCrud) {
        this.retoCrud = retoCrud;
    }

    @Override
    public List<Reto> getAll() {
        List<Reto> retos = new ArrayList<>();
        retoCrud.findAll().forEach(retoEntity -> {
            Reto reto = new Reto(retoEntity.getIdReto(),retoEntity.getTipo(),
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
            return new Reto(retoEntity.getIdReto(),retoEntity.getTipo(),
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
    public Reto getTitulo(String titulo) {
        RetoEntity retoEntity = retoCrud.findFirstByTitulo(titulo);
        if(retoEntity!=null){
            return new Reto(retoEntity.getIdReto(),retoEntity.getTipo(),
                    retoEntity.getTitulo(),retoEntity.getDescripcion(),
                    retoEntity.getComentario(),"t".equals(retoEntity.getEstado()));
        }else{
            return null;
        }
    }

    @Override
    public Boolean save(Reto reto) {
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
        if(retoCrud.findByIdReto(idReto)!=null){
            RetoEntity retoEntity =  retoCrud.findByIdReto(idReto);
           retoEntity.setEstado("f");
            retoCrud.save(retoEntity);
            return true;
        }else{
            return false;
        }
    }
}
