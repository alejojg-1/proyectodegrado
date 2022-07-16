package co.proyectoGrado.proyectoGrado.domain.repository.persistence;

import co.proyectoGrado.proyectoGrado.domain.model.CursoContenido;
import co.proyectoGrado.proyectoGrado.domain.repository.CursoContenidoRepository;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CategoriaContenidoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoContenidoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.crud.CursoCrud;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CategoriaContenidoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoContenidoPK;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoContenidoEntity;
import co.proyectoGrado.proyectoGrado.domain.repository.persistence.entity.CursoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoContenidoRepositoryImpl implements CursoContenidoRepository {

    private final CursoContenidoCrud cursoContenidoCrud;
    private final CursoCrud cursoCrud;
    private final CategoriaContenidoCrud categoriaContenidoCrud;

    @Autowired
    public CursoContenidoRepositoryImpl(CursoContenidoCrud cursoContenidoCrud, CursoCrud cursoCrud, CategoriaContenidoCrud categoriaContenidoCrud) {

        this.cursoContenidoCrud = cursoContenidoCrud;
        this.cursoCrud = cursoCrud;
        this.categoriaContenidoCrud = categoriaContenidoCrud;
    }


    @Override
    public List<CursoContenido> getAll() {
        List<CursoContenido> cursoContenidos = new ArrayList<>();

        cursoContenidoCrud.findAll().forEach(cursoContenidoEntity -> {
            CursoContenido cursoContenido = new CursoContenido( cursoContenidoEntity.getId().getIdCursoContenido().longValue(),
                    cursoContenidoEntity.getCategoriaContenido().getIdCategoriaContenido(), cursoContenidoEntity.getCurso().getIdCursos()
                    , cursoContenidoEntity.getComentario(), cursoContenidoEntity.getImagen(), cursoContenidoEntity.getComentario()
                    , cursoContenidoEntity.getDescripcion());

            cursoContenidos.add(cursoContenido);
        });

        return cursoContenidos;
    }

    @Override
    public CursoContenido getIdCurso(int idCursoContenido) {
        CursoContenidoEntity cursoContenidoEntity = cursoContenidoCrud.findFirstById_IdCursoContenido(idCursoContenido);

        if (cursoContenidoEntity != null) {
            return new CursoContenido(cursoContenidoEntity.getId().getIdCursoContenido(),
                    cursoContenidoEntity.getCategoriaContenido().getIdCategoriaContenido(), cursoContenidoEntity.getCurso().getIdCursos()
                    , cursoContenidoEntity.getComentario(), cursoContenidoEntity.getImagen(), cursoContenidoEntity.getComentario()
                    , cursoContenidoEntity.getDescripcion());
        } else {
            return null;
        }
    }

    @Override
    public List<CursoContenido> getByIdCurso(int idCurso) {
        List<CursoContenido> listaCursoContenido = new ArrayList<>();
        cursoContenidoCrud.findById_IdCursos(idCurso).forEach(cursoContenidoEntity -> {
            CursoContenido cursoContenido = new CursoContenido(cursoContenidoEntity.getId().getIdCursoContenido(),
                    cursoContenidoEntity.getId().getIdCategoriaContenido(), cursoContenidoEntity.getId().getIdCursos(),
                    cursoContenidoEntity.getComentario(), cursoContenidoEntity.getDescripcion(),
                    cursoContenidoEntity.getImagen(), cursoContenidoEntity.getVideo());
            listaCursoContenido.add(cursoContenido);
        });
        return listaCursoContenido;
    }

    @Override
    public List<CursoContenido> getByIdCategoriaYIdCurso(int idCategoriaContenido, int idCurso) {
        List<CursoContenidoEntity> listaCursoContenidoEntity = cursoContenidoCrud.
                findById_IdCategoriaContenidoAndId_IdCursos(idCategoriaContenido,idCurso);

        if (listaCursoContenidoEntity != null && !listaCursoContenidoEntity.isEmpty()) {
            List<CursoContenido> listaCursoContenido = new ArrayList<>();
            listaCursoContenidoEntity.forEach( cursoContenidoEntity -> {
                CursoContenido cursoContenido = new CursoContenido(cursoContenidoEntity.getId().getIdCursoContenido(),
                        cursoContenidoEntity.getCategoriaContenido().getIdCategoriaContenido(), cursoContenidoEntity.getCurso().getIdCursos()
                        , cursoContenidoEntity.getComentario(), cursoContenidoEntity.getDescripcion(), cursoContenidoEntity.getImagen()
                        , cursoContenidoEntity.getVideo());
                listaCursoContenido.add(cursoContenido);
            });
            return listaCursoContenido;
        } else {
            return new ArrayList<>();
        }
    }


    @Override
    public CursoContenido getCursosiId(int idCurso) {

        CursoContenidoEntity cursoContenidoEntity = cursoContenidoCrud.findByCurso_IdCursos(idCurso);

        if (cursoContenidoEntity != null) {
            //Corregir con el de arriba
            return new CursoContenido(cursoContenidoEntity.getId().getIdCursoContenido(),
                    cursoContenidoEntity.getCategoriaContenido().getIdCategoriaContenido(), cursoContenidoEntity.getCurso().getIdCursos()
                    , cursoContenidoEntity.getComentario(), cursoContenidoEntity.getImagen(), cursoContenidoEntity.getComentario()
                    , cursoContenidoEntity.getDescripcion());
        } else {
            return null;
        }
    }


    @Override
    public boolean save(CursoContenido cursoContenido) {
        try {

           CategoriaContenidoEntity categoriaContenidoEntity = categoriaContenidoCrud.
                   findFirstByIdCategoriaContenido(cursoContenido.getIdCategoriaContenido());
           CursoEntity cursoEntity = cursoCrud.findFirstByIdCursos(cursoContenido.getIdCurso());

            CursoContenidoPK cursoContenidoPK = new CursoContenidoPK(cursoContenido.getIdCursoContenido()
                    ,cursoContenido.getIdCategoriaContenido(),
                    cursoContenido.getIdCurso());

            CursoContenidoEntity cursoContenidoEntity = new CursoContenidoEntity(
                    cursoContenidoPK, cursoContenido.getComentario(), cursoContenido.getDescripcion(),
                    cursoContenido.getImagen(), cursoContenido.getVideo(),categoriaContenidoEntity,cursoEntity);
            cursoContenidoCrud.save(cursoContenidoEntity);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean actualizar(int id, CursoContenido cursoContenido) {
        try {
            CursoContenidoEntity cursoContenidoEntity = new CursoContenidoEntity();

            cursoContenidoEntity.getId().setIdCursoContenido(cursoContenido.getIdCursoContenido());
            cursoContenidoEntity.getCategoriaContenido().setIdCategoriaContenido(cursoContenido.getIdCategoriaContenido());
            cursoContenidoEntity.getCurso().setIdCursos(cursoContenido.getIdCurso());
            cursoContenidoEntity.setComentario(cursoContenido.getComentario());
            cursoContenidoEntity.setImagen(cursoContenido.getImagen());
            cursoContenidoEntity.setDescripcion(cursoContenido.getDescripcion());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int idCursoContenido) {
        if (cursoContenidoCrud.findFirstById_IdCursoContenido(idCursoContenido) != null) {
            CursoContenidoEntity cursoContenidoEntity = cursoContenidoCrud.findFirstById_IdCursoContenido(idCursoContenido);
            cursoContenidoCrud.save(cursoContenidoEntity);
            return true;
        } else {
            return false;
        }
    }

}
