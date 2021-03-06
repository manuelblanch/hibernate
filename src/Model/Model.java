/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import entitats.Accesoris;
import entitats.Competencia;
import entitats.Consola;
import entitats.Jocs;
import org.hibernate.Session;
import utils.HibernateUtil;

/**
 *
 * @author manuel
 */
public class Model {
    
    private static Session sessio = HibernateUtil.getSessionFactory().openSession();
    private Consola1to1DAO<Consola> getClasseDAOConsoles = new Consola1to1DAO<>(Consola.class, sessio);
    private Consola1to1DAO<Accesoris> getClasseDAOAccesoris = new Consola1to1DAO<>(Accesoris.class, sessio);
    private Consola1to1DAO<Jocs> getClasseDAOJocs = new Consola1to1DAO<>(Jocs.class, sessio);
    private Consola1to1DAO<Competencia> getclasseDAOCompetencia = new Consola1to1DAO<>(Competencia.class, sessio);

    public Consola1to1DAO<Consola> getClasseDAOConsoles() {
        return getClasseDAOConsoles;
    }
    
    public Consola1to1DAO<Accesoris> getClasseDAOAccesoris() {
        return getClasseDAOAccesoris;
    }
    
    public Consola1to1DAO<Jocs> getClasseDAOJocs() {
        return getClasseDAOJocs;
    }


    public Consola1to1DAO<Competencia> getClasseDAOCompetencia() {
        return getclasseDAOCompetencia;
    }

    public void tancarSessio() {
        System.out.println("Closing");
        sessio.close();
}
    
}

