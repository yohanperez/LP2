/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.controlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa.Entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import jpa.Entity.Negocio;
import jpa.controlador.exceptions.NonexistentEntityException;
import jpa.controlador.exceptions.PreexistingEntityException;

/**
 *
 * @author Administrator
 */
public class NegocioJpaController implements Serializable {

    public NegocioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Negocio negocio) throws PreexistingEntityException, Exception {
        if (negocio.getProductoList() == null) {
            negocio.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : negocio.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getIdProducto());
                attachedProductoList.add(productoListProductoToAttach);
            }
            negocio.setProductoList(attachedProductoList);
            em.persist(negocio);
            for (Producto productoListProducto : negocio.getProductoList()) {
                Negocio oldIdNegocioOfProductoListProducto = productoListProducto.getIdNegocio();
                productoListProducto.setIdNegocio(negocio);
                productoListProducto = em.merge(productoListProducto);
                if (oldIdNegocioOfProductoListProducto != null) {
                    oldIdNegocioOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldIdNegocioOfProductoListProducto = em.merge(oldIdNegocioOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNegocio(negocio.getIdNegocio()) != null) {
                throw new PreexistingEntityException("Negocio " + negocio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Negocio negocio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Negocio persistentNegocio = em.find(Negocio.class, negocio.getIdNegocio());
            List<Producto> productoListOld = persistentNegocio.getProductoList();
            List<Producto> productoListNew = negocio.getProductoList();
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getIdProducto());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            negocio.setProductoList(productoListNew);
            negocio = em.merge(negocio);
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    productoListOldProducto.setIdNegocio(null);
                    productoListOldProducto = em.merge(productoListOldProducto);
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Negocio oldIdNegocioOfProductoListNewProducto = productoListNewProducto.getIdNegocio();
                    productoListNewProducto.setIdNegocio(negocio);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldIdNegocioOfProductoListNewProducto != null && !oldIdNegocioOfProductoListNewProducto.equals(negocio)) {
                        oldIdNegocioOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldIdNegocioOfProductoListNewProducto = em.merge(oldIdNegocioOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = negocio.getIdNegocio();
                if (findNegocio(id) == null) {
                    throw new NonexistentEntityException("The negocio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Negocio negocio;
            try {
                negocio = em.getReference(Negocio.class, id);
                negocio.getIdNegocio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The negocio with id " + id + " no longer exists.", enfe);
            }
            List<Producto> productoList = negocio.getProductoList();
            for (Producto productoListProducto : productoList) {
                productoListProducto.setIdNegocio(null);
                productoListProducto = em.merge(productoListProducto);
            }
            em.remove(negocio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Negocio> findNegocioEntities() {
        return findNegocioEntities(true, -1, -1);
    }

    public List<Negocio> findNegocioEntities(int maxResults, int firstResult) {
        return findNegocioEntities(false, maxResults, firstResult);
    }

    private List<Negocio> findNegocioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Negocio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Negocio findNegocio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Negocio.class, id);
        } finally {
            em.close();
        }
    }

    public int getNegocioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Negocio> rt = cq.from(Negocio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
