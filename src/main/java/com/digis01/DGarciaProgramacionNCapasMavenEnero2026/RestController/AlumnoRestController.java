package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.RestController;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOJPAImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    
    @GetMapping()
    @PreAuthorize("hasRole('1er Semestre')") //hasAnyRole 
    public ResponseEntity GetAll() {
        try {
            Result result = alumnoJPADAOImplementation.GetAll();

            if (result.correct) {
                if (result.objects != null || !result.objects.isEmpty()) {
//                return ResponseEntity.status(200).body(result.objects);
                    return ResponseEntity.ok(result);
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
    
    
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity Add(@RequestPart("datos") Map<String, Object> datos, @RequestPart(name = "imagen", required = false) MultipartFile imagen){
        /*proeso interno*/
        return ResponseEntity.ok(this);
    }
    
    @GetMapping("test")
    public ResponseEntity test(Authentication authentication){
        Map<String, Object> json = new HashMap<>();
        
        json.put("user", authentication.getName());
        json.put("rol", authentication.getAuthorities());
        
        return ResponseEntity.ok(json);
    }
    

}
