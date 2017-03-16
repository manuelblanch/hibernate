/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author manuel
 */
import entitats.Consola;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

/**
 *
 * @author profe
 */
public class ConsolaDAO {

    private Session sesion;
    private Transaction tx;

    public long guardaConsola(Consola consola) throws HibernateException {
        long id = 0;

        try {
            iniciaOperacio();
            id = (Long) sesion.save(consola);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }

        return id;
    }

    public void actualitzaConsola(Consola consola) throws HibernateException {
        try {
            iniciaOperacio();
            sesion.update(consola);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminaConsola(Consola consola) throws HibernateException {
        try {
            iniciaOperacio();
            sesion.delete(consola);
            tx.commit();
        } catch (HibernateException he) {
            tractaExcepcio(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public Consola obtenConsola(long idConsola) throws HibernateException {
        Consola consola = null;
        try {
            iniciaOperacio();
            consola = (Consola) sesion.get(Consola.class, idConsola);
        } finally {
            sesion.close();
        }

        return consola;
    }

    public List<Consola> obtenLlistaConsoles() throws HibernateException {
        List<Consola> llistaConsoles = null;

        try {
            iniciaOperacio();
            llistaConsoles = sesion.createQuery("from Consola").list();
        } finally {
            sesion.close();
        }

        return llistaConsoles;
    }

    private void iniciaOperacio() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void tractaExcepcio(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Error a la capa d'accés a dades", he);
    }
}
