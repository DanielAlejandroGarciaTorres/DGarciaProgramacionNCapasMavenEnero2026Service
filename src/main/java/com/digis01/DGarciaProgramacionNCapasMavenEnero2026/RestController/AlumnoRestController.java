package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.RestController;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOJPAImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/alumno")
public class AlumnoRestController {

    @Autowired
    private AlumnoDAOJPAImplementation alumnoJPADAOImplementation;

    /* /api/alumno
    GET - GetAll - /api/alumno
    GET - GetById - /api/alumno/{idalumno}
    POST - Add - /api/alumno ** body - Alumno
    PUT - Update - /api/alumno ** body - Alumno
    DELETE - Delete - /api/alumno/{idalumno}
    PATCH - StatusChange - /api/alumno/status/{idalumno}
                        - /api/alumno?status=n;
                        - /api/alumno/password ** body alumno/password - JSON 
    */
    
    @GetMapping
    public ResponseEntity GetAll() {

        try {
            Result result = alumnoJPADAOImplementation.GetAll();

            if (result.correct) {
                if (result.objects != null || !result.objects.isEmpty()) {
//                return ResponseEntity.status(200).body(result.objects);
                    return ResponseEntity.ok(result.objects);
                } else {
                    return ResponseEntity.noContent().build();
                }
            } else {
                return ResponseEntity.badRequest().body(result.errorMessage);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex);
        }

    }

}
