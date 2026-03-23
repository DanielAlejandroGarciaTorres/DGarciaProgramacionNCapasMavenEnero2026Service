package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.RestController;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service.JwtService;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.Service.UserDetailServiceImplementation;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailServiceImplementation userDetailsService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.get("email"), 
                loginRequest.get("password")
            )
        );

        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.get("email"));
        // si todo fue correcto
        String token = jwtService.generateToken(user);
        // api key 
        Map<String, Object> map = new HashMap<>();
        map.put("Key", token);
        return ResponseEntity.ok(map);
    }
}