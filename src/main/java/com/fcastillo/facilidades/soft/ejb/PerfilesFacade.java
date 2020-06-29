/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Perfiles;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class PerfilesFacade extends AbstractFacade<Perfiles> implements PerfilesFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilesFacade() {
        super(Perfiles.class);
    }

    @Override
    public Perfiles findById(int id) {
        String consulta = "FROM Perfiles p WHERE p.id=?1";
        Query query = em.createQuery(consulta).setParameter(1, id);
        return (Perfiles) query.getSingleResult();
    }

}
