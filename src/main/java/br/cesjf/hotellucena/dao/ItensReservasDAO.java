package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.ItensReservas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;
import java.util.Date;

public class ItensReservasDAO {

    public static ItensReservasDAO itemreservaDAO;

    public static ItensReservasDAO getInstance() {
        if (itemreservaDAO == null) {
            itemreservaDAO = new ItensReservasDAO();
        }
        return itemreservaDAO;
    }

    public List<ItensReservas> buscarItensReservas(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Itens_Reservas a where a.itensidItem.idItem =:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<ItensReservas> buscarReservasItens(int id) {
        EntityManager em = PersistenceUtil.getEntityManager();

        Query query = em.createQuery("select a from Itens_Reservas a where a.reservascodigoReserva.codigoReserva =:id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public ItensReservas buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Itens_Reservas a where a.idItem =:nome || a.codigoRserva =:nome");
        query.setParameter("nome", nome);

        List<ItensReservas> itensreservas = query.getResultList();
        if (itensreservas != null && itensreservas.size() > 0) {
            return itensreservas.get(0);
        }

        return null;
    }

    public List<ItensReservas> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Itens_Reservas As a");
        return query.getResultList();
    }

    public void remover(ItensReservas itemreserva) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(itemreserva)) {
            itemreserva = em.merge(itemreserva);
        }
        em.remove(itemreserva);
        em.getTransaction().commit();
    }

    public ItensReservas persistir(ItensReservas itemreserva) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            itemreserva = em.merge(itemreserva);
            em.getTransaction().commit();
            System.out.println("Registro Itens das Reservas gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemreserva;
    }
    
    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete from Itens_Reservas ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

 }
