/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Personas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fcastillo
 */
@Local
public interface PersonasFacadeLocal {

    void create(Personas personas);

    void edit(Personas personas);

    void remove(Personas personas);

    Personas find(Object id);

    Personas findById(int id);

    List<Personas> findAll();

    List<Personas> findRange(int[] range);

    int count();

}
