/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    Usuarios findByUsername(String username) throws Exception;

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();

}
