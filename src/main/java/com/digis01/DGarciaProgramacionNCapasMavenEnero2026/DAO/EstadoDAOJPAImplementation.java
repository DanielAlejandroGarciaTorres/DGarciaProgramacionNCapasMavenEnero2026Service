package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Estado;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 
public class EstadoDAOJPAImplementation implements IEstado {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByPais(int idPais) {
        Result result = new Result();

        try {
            TypedQuery<Estado> queryEstado = entityManager.createQuery("FROM Estado WHERE Pais.IdPais = :pIdPais", Estado.class);
            queryEstado.setParameter("pIdPais", idPais);

            result.objects = new ArrayList<>(queryEstado.getResultList());
            
            result.correct = true;
        } catch (Exception ex) {

            result.objects = null;
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

}
