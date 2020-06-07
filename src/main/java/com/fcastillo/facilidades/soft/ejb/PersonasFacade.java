/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Personas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class PersonasFacade extends AbstractFacade<Personas> implements PersonasFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonasFacade() {
        super(Personas.class);
    }

    @Override
    public Personas findById(int id) {
        Personas persona = null;
        String consulta = "FROM Personas p WHERE p.id=?1";
        try {
            Query q = em.createQuery(consulta);
            q.setParameter(1, id);
            persona = (Personas) q.getSingleResult();
        } catch (Exception e) {
        }
        return persona;
    }

}
