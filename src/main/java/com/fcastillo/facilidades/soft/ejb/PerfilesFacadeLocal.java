/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Perfiles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PerfilesFacadeLocal {

    void create(Perfiles perfiles);

    void edit(Perfiles perfiles);

    void remove(Perfiles perfiles);

    Perfiles find(Object id);

    Perfiles findById(int id);

    List<Perfiles> findAll();

    List<Perfiles> findRange(int[] range);

    int count();

}
