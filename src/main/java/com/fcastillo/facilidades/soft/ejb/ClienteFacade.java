/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    @Override
    public List<Cliente> findByNameLike(String nombre) {
        Query query = em.createQuery("FROM Cliente c WHERE CONCAT (lower(c.id.nombre),'',lower(c.id.apellido)) like ?1");
        nombre = "%" + nombre.trim() + "%";
        return query.setParameter(1, nombre).getResultList();
    }

    @Override
    public Cliente findById(int id) {
        String consulta;
        Cliente cliente = null;
        try {
            consulta = "FROM Cliente c WHERE c.id.id=?1";
            Query q = em.createQuery(consulta);
            q.setParameter(1, id);
            cliente = (Cliente) q.getSingleResult();
        } catch (Exception e) {
            throw e;
        }
        return cliente;
    }

}
