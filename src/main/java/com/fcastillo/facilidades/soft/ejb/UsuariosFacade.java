/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios findByUsername(String username) throws Exception {
        Usuarios usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuarios u WHERE u.username=?1";
            Query q = em.createQuery(consulta).setParameter(1, username);
            usuario = (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Ud. no se encuentra registrado como usuario");
        }

        return usuario;
    }

}
