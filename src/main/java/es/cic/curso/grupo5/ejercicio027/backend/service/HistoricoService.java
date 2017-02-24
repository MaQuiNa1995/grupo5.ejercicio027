/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cic.curso.grupo5.ejercicio027.backend.service;

import java.util.List;

import es.cic.curso.grupo5.ejercicio027.backend.dominio.Historico;

/**
 *
 * @author MaQuiNa
 */
public interface HistoricoService {

   

    Historico aniadirHistorico(Historico historico);

    void borrarHistorico(Long id);
    
    Historico modificarHistorico(Historico historico);

    Historico obtenerHistorico(Long id);

    List<Historico> listarHistorico();
}
