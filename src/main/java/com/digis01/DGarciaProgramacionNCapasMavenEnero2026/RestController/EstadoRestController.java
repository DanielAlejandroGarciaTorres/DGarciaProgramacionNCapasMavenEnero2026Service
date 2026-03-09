package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.RestController;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.EstadoDAOJPAImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/estado")
//@CrossOrigin()
public class EstadoRestController {

    @Autowired
    private EstadoDAOJPAImplementation estadoDAOJPAImplementation;
    
    @GetMapping
    public ResponseEntity GetByPais(@RequestParam("IdPais") int idPais){
        
        Result result;
        
        try{
            result = estadoDAOJPAImplementation.GetByPais(idPais);
        } catch (Exception ex){
            return ResponseEntity.status(500).body(ex);
        }
       return ResponseEntity.ok(result); 
    }
}
