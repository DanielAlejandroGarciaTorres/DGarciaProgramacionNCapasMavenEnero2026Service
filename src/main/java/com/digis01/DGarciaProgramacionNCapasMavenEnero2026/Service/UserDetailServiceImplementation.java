package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO.AlumnoDAOJPAImplementation;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImplementation implements  UserDetailsService{

    @Autowired
    private AlumnoDAOJPAImplementation alumnoDAOImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Result result = alumnoDAOImpl.getByEmail(username);
        
        if (result.correct) {
            Alumno alumno = (Alumno) result.object;
            
            return User.withUsername(alumno.getEmail())
                    .password(alumno.getPassword())
                    .roles(alumno.Semestre.getNombre())
                    .build();
            
        } else {
            throw new UsernameNotFoundException("No se encontró el alumno con el correo: " + username);
        }
    }
    
    
    
    
}
