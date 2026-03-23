package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Colonia;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Direccion;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Semestre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AlumnoDAOJPAImplementation implements IAlumnoJPA {

    @Autowired
    private EntityManager entityManager; // JPA

    @Override
    public Result GetAll() {
        Result result = new Result();

        try {

            TypedQuery<Alumno> queryAlumno = entityManager.createQuery("FROM Alumno", Alumno.class);
            List<Alumno> alumnos = queryAlumno.getResultList();

            result.objects = new ArrayList<>(alumnos);

            // mapper ...  AlumnoJPA -> AlumnoML   -- modelMapper 
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    @Transactional
    public Result Add(Alumno alumno) {
        Result result = new Result();

        try {

//            direccionJPA.Alumno = alumno;
            entityManager.persist(alumno);

            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @Override
    public Result GetById(int idAlumno) {
        Result result = new Result();
        try {
            Alumno alumno = entityManager.find(Alumno.class, idAlumno);
            // jpa -> Ml
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    @Transactional
    public Result Update(Alumno alumno) {
        Result result = new Result();
        try {
            Alumno alumnoBD = entityManager.find(Alumno.class, alumno.getIdAlumno());
            if (alumno != null) { // alumno si existe
                //ML -> JPA
                Alumno alumnoJPA = new Alumno();
                alumnoJPA.Direcciones = alumnoBD.Direcciones;
                entityManager.merge(alumnoJPA);
            }
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    @Transactional
    public Result Delete(int idAlumno) {
        Result result = new Result();
        try {
            Alumno alumno = entityManager.find(Alumno.class, idAlumno);
            if (alumno != null) {
                entityManager.remove(alumno);
                result.correct = true;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontro el recurso";
            }
        } catch (Exception ex) {
        }
        return result;
    }

    @Override
    public Result getByEmail(String email) {
        Result result = new Result();

        try {

            TypedQuery<Alumno> queryAlumno = entityManager.createQuery("FROM Alumno WHERE Email = :pEmail", Alumno.class);
            queryAlumno.setParameter("pEmail", email);

            result.object = queryAlumno.getSingleResult();
            result.correct = true;

        } catch (Exception e) {
            result.correct = false;
            result.ex = e;
            result.errorMessage = e.getLocalizedMessage();

        }

        return result;
    }

}
