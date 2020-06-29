/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Prestamos;
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
public class PrestamosFacade extends AbstractFacade<Prestamos> implements PrestamosFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamosFacade() {
        super(Prestamos.class);
    }

    @Override
    public List<Prestamos> findByIdCliente(int idCliente) {
        String consulta = "FROM Prestamos p WHERE p.idcliente.id.id=?1";
        List<Prestamos> lista = null;
        TypedQuery<Prestamos> query = em.createQuery(consulta, Prestamos.class);
        lista = query.setParameter(1, idCliente).getResultList();
        return lista;
    }

}
