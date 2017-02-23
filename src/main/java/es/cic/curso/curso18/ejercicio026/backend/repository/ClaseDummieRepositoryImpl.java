package es.cic.curso.curso18.ejercicio026.backend.repository;

import es.cic.curso.curso18.ejercicio026.backend.dominio.Dummie;
import org.springframework.stereotype.Repository;

@Repository
public class ClaseDummieRepositoryImpl extends AbstractRepositoryImpl<Long, Dummie> implements ClaseDummieRepository {

    @Override
    public Class<Dummie> getClassDeT() {
        return Dummie.class;
    }

    @Override
    public String getNombreTabla() {
        /**
         * Escribelo tal cual lo tienes en el changelog
         */
        return "Dummie";
    }
}
