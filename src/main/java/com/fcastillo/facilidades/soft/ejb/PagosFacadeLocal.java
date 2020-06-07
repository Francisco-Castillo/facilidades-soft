/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Pagos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PagosFacadeLocal {

    void create(Pagos pagos);

    void edit(Pagos pagos);

    void remove(Pagos pagos);

    Pagos find(Object id);

    List<Pagos> findAll();

    List<Pagos> findRange(int[] range);

    int count();
    
}
