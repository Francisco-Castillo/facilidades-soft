/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente clientes);

    void edit(Cliente clientes);

    void remove(Cliente clientes);

    Cliente find(Object id);

    Cliente findById(int id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    List<Cliente> findByNameLike(String nombre);

    int count();

}
