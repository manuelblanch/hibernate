/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

/**
 *
 * @author manuel
 */

import Controlador.Controlador;
import Model.ConsolaDAO;
import Vista.Vista;
import entitats.Consola;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class Hibernate {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Vista vista = new Vista();
        ConsolaDAO consola = new ConsolaDAO(); 
        
        new Controlador(consola, vista);
        
//        Session sesion;
//        Transaction tx;
//        
//        sesion = HibernateUtil.getSessionFactory().openSession();
//        tx = sesion.beginTransaction();
//        Consola c = new Consola();
//        c.setNom("Supernintendo");
//        c.setMarca("Nintendo");
//        c.setCompetencia("Sony");
//        
//        sesion.save(c);
//        
//        tx.commit();
// TODO code application logic here
//        ConsolaDAO consolaDAO = new ConsolaDAO();
//        Consola consolaRecuperada = null;
//        long idAEliminar = 0;
//
////Creem tres instàncies de Consola
//        Consola consola1 = new Consola(1, "Consola 1", "Sony", "Mario");
//        Consola consola2 = new Consola(2, "Conaola 2", "Nintendo", "Crash");
//        Consola consola3 = new Consola(3, "Consola 3", "Microsoft", "Sea of thieves");
//
////Guardem les tres instàncies, i copiem l'id de la consola1 per usar-lo posteriorment
//        idAEliminar = consolaDAO.guardaConsola(consola1);
//        consolaDAO.guardaConsola(consola2);
//        consolaDAO.guardaConsola(consola3);
//
////Modifiquem la consola 2 i l'actualitzem
//        consola2.setNom("Nova Consola 2");
//        consolaDAO.actualitzaConsola(consola2);
//
////Recuperem la consola1 de la base de dades
//        consolaRecuperada = consolaDAO.obtenConsola(idAEliminar);
//        System.out.println("Recuperem la " + consolaRecuperada.getNom());
//
////Eliminem la consolaRecuperada (que és la consola3)
//        consolaDAO.eliminaConsola(consolaRecuperada);
//
////Obtenim la llista de consoles que queden a la base de dades i la mostrem
//        List<Consola> llistaConsoles = consolaDAO.obtenLlistaConsoles();
//        System.out.println("Hi ha " + llistaConsoles.size() + "consoles a la base de dades.");
//
//        for (Consola c : llistaConsoles) {
//            System.out.println("-> " + c.getNom());
//        }
//
//        System.exit(0);
//
//    }

}
}
