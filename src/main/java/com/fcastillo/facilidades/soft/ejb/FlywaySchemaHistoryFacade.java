/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.FlywaySchemaHistory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fcastillo
 */
@Stateless
public class FlywaySchemaHistoryFacade extends AbstractFacade<FlywaySchemaHistory> implements FlywaySchemaHistoryFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlywaySchemaHistoryFacade() {
        super(FlywaySchemaHistory.class);
    }
    
}
