/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Prestamo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author fcastillo
 */
@Stateless
public class PrestamoFacade extends AbstractFacade<Prestamo> implements PrestamoFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamoFacade() {
        super(Prestamo.class);
    }

    @Override
    public List<Prestamo> findByIdCliente(int idCliente) {
        String consulta = "FROM Prestamo p WHERE p.idcliente.id.id=?1";
        List<Prestamo> lista = null;
        TypedQuery<Prestamo> query = em.createQuery(consulta, Prestamo.class);
        lista = query.setParameter(1, idCliente).getResultList();
        return lista;
    }

}
