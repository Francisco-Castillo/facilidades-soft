/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Prestamo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PrestamoFacadeLocal {

    void create(Prestamo prestamos);

    void edit(Prestamo prestamos);

    void remove(Prestamo prestamos);

    Prestamo find(Object id);

    List<Prestamo> findAll();

    List<Prestamo> findRange(int[] range);

    int count();

    List<Prestamo> findByIdCliente(int idCliente);

}
