/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Clientes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface ClientesFacadeLocal {

    void create(Clientes clientes);

    void edit(Clientes clientes);

    void remove(Clientes clientes);

    Clientes find(Object id);

    Clientes findById(int id);

    List<Clientes> findAll();

    List<Clientes> findRange(int[] range);

    List<Clientes> findByNameLike(String nombre);

    int count();

}
