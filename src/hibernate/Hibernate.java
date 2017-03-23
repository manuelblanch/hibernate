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
import Model.Consola1to1DAO;
import Model.ConsolaDAO;
import Model.Model;
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
        Model model = new Model();
        //ConsolaDAO model = new ConsolaDAO();
        //Consola1to1DAO model = new Consola1to1DAO();

        new Controlador(model, vista);


    }
}
