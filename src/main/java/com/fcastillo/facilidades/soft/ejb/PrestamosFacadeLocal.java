/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Prestamos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PrestamosFacadeLocal {

    void create(Prestamos prestamos);

    void edit(Prestamos prestamos);

    void remove(Prestamos prestamos);

    Prestamos find(Object id);

    List<Prestamos> findAll();

    List<Prestamos> findRange(int[] range);

    int count();

    List<Prestamos> findByIdCliente(int idCliente);

}
