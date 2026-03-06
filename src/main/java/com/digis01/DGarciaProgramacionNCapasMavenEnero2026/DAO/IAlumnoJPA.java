package com.digis01.DGarciaProgramacionNCapasMavenEnero2026.DAO;

import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Alumno;
import com.digis01.DGarciaProgramacionNCapasMavenEnero2026.JPA.Result;


public interface IAlumnoJPA {

    Result GetAll();
    Result Add(Alumno alumno);
    Result GetById(int idAlumno);
    Result Update(Alumno alumno);
    Result Delete(int idAlumno);
}
