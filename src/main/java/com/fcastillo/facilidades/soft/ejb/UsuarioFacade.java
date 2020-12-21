/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.ejb;

import com.fcastillo.facilidades.soft.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fcastillo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.fcastillo_facsoft_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario findByUsername(String username) throws Exception {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuarios u WHERE u.username=?1";
            Query q = em.createQuery(consulta).setParameter(1, username);
            usuario = (Usuario) q.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Ud. no se encuentra registrado como usuario");
        }

        return usuario;
    }

}
