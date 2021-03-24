/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Administrator
 */
public class pruebas {
    public static void main(String []args){
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        et.begin();
       // StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cambio");
        StoredProcedureQuery spq = em.createStoredProcedureQuery("fb_cambioN");
        spq.registerStoredProcedureParameter("uss",String.class,ParameterMode.IN);
        spq.registerStoredProcedureParameter("nombre_us",String.class,ParameterMode.IN);
        spq.setParameter("uss","1");
        spq.setParameter("nombre_us", "vic");
        spq.execute();
        et.commit();
        em.close();
    }
}
