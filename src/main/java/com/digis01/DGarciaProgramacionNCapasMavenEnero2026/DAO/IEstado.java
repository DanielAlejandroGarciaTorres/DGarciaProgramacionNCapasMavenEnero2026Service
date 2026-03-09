package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;


public interface IEstado {

    Result GetByPais(int idPais);
    
}
