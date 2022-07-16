package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CategoriaContenido;
import co.proyectoGrado.proyectoGrado.domain.repository.CategoriaContenidoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CategoriaContenidoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoContenidoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.PreguntaCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CategoriaContenidoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.PreguntaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoriaContenidoRespositoryImpl implements CategoriaContenidoRepository {

    private final CategoriaContenidoCrud categoriaContenidoCrud;
    private final PreguntaCrud preguntaCrud;
    private final CursoContenidoCrud cursoContenidoCrud;

    @Autowired
    public CategoriaContenidoRespositoryImpl(CategoriaContenidoCrud categoriaContenidoCrud, PreguntaCrud preguntaCrud, CursoContenidoCrud cursoContenidoCrud) {

        this.categoriaContenidoCrud = categoriaContenidoCrud;
        this.preguntaCrud = preguntaCrud;
        this.cursoContenidoCrud = cursoContenidoCrud;
    }


    @Override
    public List<CategoriaContenido> getAll() {
        List<CategoriaContenido> categoriaContenidos = new ArrayList<>();

        categoriaContenidoCrud.findAll().forEach(categoriaContenidoEntity -> {
            CategoriaContenido categoriaContenido = new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdpreguntas(), categoriaContenidoEntity.getNombre());

            categoriaContenidos.add(categoriaContenido);
        });

        return categoriaContenidos;
    }

    @Override
    public List<CategoriaContenido> getByIds(List<Integer> listaIdsCategoriaContenido) {
        List<CategoriaContenido> listaCategoriaContenido = new ArrayList<>();
        categoriaContenidoCrud.findByIdCategoriaContenidoIn(listaIdsCategoriaContenido).forEach(categoriaContenidoEntity -> {
            CategoriaContenido categoriaContenido = new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdpreguntas(), categoriaContenidoEntity.getNombre());
            listaCategoriaContenido.add(categoriaContenido);
        });
        return listaCategoriaContenido;
    }

    @Override
    public CategoriaContenido get(int idCategoriaContenido) {

        CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findByIdCategoriaContenido(idCategoriaContenido);

        if (categoriaContenidoEntity != null) {
            return new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdpreguntas(), categoriaContenidoEntity.getNombre());
        } else {
            return null;
        }
    }

    @Override
    public CategoriaContenido getPregunta(int idPregunta) {

        CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findByPregunta_IdPregunta(idPregunta);

        if (categoriaContenidoEntity != null) {
            return new CategoriaContenido(categoriaContenidoEntity.getIdCategoriaContenido(),
                    categoriaContenidoEntity.getIdpreguntas(), categoriaContenidoEntity.getNombre());
        } else {
            return null;
        }
    }

    @Override
    public boolean save(CategoriaContenido categoriaContenido) {
        try {
            PreguntaEntity preguntaEntity = new PreguntaEntity();
            if(categoriaContenido.getIdPregunta() != null){
                preguntaEntity = preguntaCrud.findFirstByIdPregunta(categoriaContenido.getIdPregunta());
            }else{
                preguntaEntity = null;
            }
            // CursoContenidoEntity cursoContenidoEntity= cursoContenidoCrud.findFirstByIdCursoContenido(categoriaContenido.getIdCategoriaContenido());
            CategoriaContenidoEntity categoriaContenidoEntity = new CategoriaContenidoEntity(categoriaContenido.getIdCategoriaContenido(),
                    categoriaContenido.getIdPregunta(),categoriaContenido.getNombre(),preguntaEntity,null);


            categoriaContenidoCrud.save(categoriaContenidoEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean actualizar(int id, CategoriaContenido categoriaContenido) {
        try {
            CategoriaContenidoEntity categoriaContenidoEntity = new CategoriaContenidoEntity();
            categoriaContenidoEntity.setIdCategoriaContenido(categoriaContenido.getIdCategoriaContenido());
            categoriaContenidoEntity.setIdpreguntas(categoriaContenido.getIdPregunta());
            //categoriaContenidoEntity.setPregunta(preguntasCrud.findById(categoriaContenido.getIdPregunta()));
            categoriaContenidoCrud.save(categoriaContenidoEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean delete(int idCategoriaContenido) {
        if (categoriaContenidoCrud.findByIdCategoriaContenido(idCategoriaContenido) != null) {
            CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.findFirstByIdCategoriaContenido(idCategoriaContenido);
            categoriaContenidoCrud.delete(categoriaContenidoEntity);
            return true;
        } else {
            return false;
        }
    }
}
