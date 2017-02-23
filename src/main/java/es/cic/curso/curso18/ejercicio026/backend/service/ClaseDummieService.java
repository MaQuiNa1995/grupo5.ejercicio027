/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.curso18.ejercicio026.backend.service;

import es.cic.curso.curso18.ejercicio026.backend.dominio.Dummie;
import java.util.List;

/**
 *
 * @author MaQuiNa
 */
public interface ClaseDummieService {

    Dummie actualizarClaseDummie(Dummie modificada);

    Long aniadirClaseDummie(String palabra, boolean apagado, int numero, long numeroGran, float decimal);

    void borrarClaseDummie(Long id);

    Dummie obtenerClaseDummie(Long id);

    List<Dummie> obtenerClaseDummies();
}
