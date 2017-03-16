/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author manuel
 */

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author profe
 */
public class CarregaTaula {
    
    //Mètode que carrega els objectes continguts a l'ArrayList i el mostra a la JTable. La classe indica de quin tipo són els objectes de l'ArrayList
    //Si volem que es pugue modificar les dades directament des de la taula hauríem d'usar el model instància de la classe ModelCanvisBD, que varia d'una BD a una altra
    //Esta versió afegix a la darrera columna de la taula l'objecte mostrat a la mateixa de manera que el podrem recuperar fàcilment per fer updates, deletes, etc...
    //Esta columna extra no apareix a la taula ja que la borrem, però la retornem per poder-la afegir quan sigue necessari
    public static TableColumn carregaTaula(ArrayList resultSet, JTable taula, Class<?> classe) {

        //variables locals
        Vector columnNames = new Vector();
        Vector data = new Vector();
        //Per poder actualitzar la BD des de la taula usaríem el model comentat
        //ModelCanvisBD model;
        DefaultTableModel model;
        
        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();
        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrdenarCampClasseAlfabeticament());
        int ncamps = camps.length;
        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar _numero_ davant el nom dels camps, mostrem el nom a partir de la 4ª lletra 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(3));
        }
        //Afegixo al model de la taula una columna on guardaré l'objecte mostrat a cada fila (amago la columna al final per a que no aparegue a la vista)
        columnNames.addElement("objecte");
        //Si hi ha algun element a l'arraylist omplim la taula
        if (resultSet.size() != 0) {

            //Guardem els descriptors de mètode que ens interessen (els getters), més una columna per guardar l'objecte sencer
            Vector<Method> methods = new Vector(ncamps + 1);
            try {

                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrdenarMetodeClasseAlfabeticament());
                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();
                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (IntrospectionException ex) {
                //Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Object m : resultSet) {
                Vector row = new Vector(ncamps + 1);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException ex) {
                        //Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        //Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        //Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //Aquí guardo l'objecte sencer a la darrera columna
                row.addElement(m);
                //Finalment afegixo la fila a les dades
                data.addElement(row);
            }
        }

        //Utilitzem el model que permet actualitzar la BD des de la taula
        //model = new ModelCanvisBD(data, columnNames, Model.getConnexio(), columnNames.size() - 1);
        model=new DefaultTableModel(data, columnNames);
        taula.setModel(model);

        //Borro la darrera columna per a que no aparegue a la vista, però abans la guardo en una variable que al final serà el que retorna el mètode
        TableColumnModel tcm = taula.getColumnModel();
        TableColumn columna=tcm.getColumn(tcm.getColumnCount() - 1);
        tcm.removeColumn(columna);

        //Fixo l'amplada de les columnes que sí es mostren
        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
        
        return columna;

    }        
    
    public static void carregaTaula_v2(ArrayList resultSet, JTable taula, Class<?> classe) {
        // TODO add your handling code here:
        //Quan tornem a carregar la taula perdem la selecció que haviem fet i posem filasel a -1
        
        Vector columnNames = new Vector();
        Vector data = new Vector();
        DefaultTableModel model;

        //Anotem el nº de camps de la classe
        Field[] camps = classe.getDeclaredFields();

        //Ordenem els camps alfabèticament
        Arrays.sort(camps, new OrdenarCampClasseAlfabeticament());
        int ncamps = camps.length;

        //Recorrem els camps de la classe i posem els seus noms com a columnes de la taula
        //Com hem hagut de posar numero davant el nom dels camps, mostrem el nom a partir de la 4ª lletra 
        for (Field f : camps) {
            columnNames.addElement(f.getName().substring(3));
        }

        //Si hi ha algun element a l'arraylist omplim la taula
        if (resultSet.size() != 0) {
            //Guardem els descriptors de mètode que ens interessen (els getters)
            Vector<Method> methods = new Vector(resultSet.size());

            try {
                PropertyDescriptor[] descriptors = Introspector.getBeanInfo(classe).getPropertyDescriptors();
                Arrays.sort(descriptors, new OrdenarMetodeClasseAlfabeticament());

                for (PropertyDescriptor pD : descriptors) {
                    Method m = pD.getReadMethod();

                    if (m != null & !m.getName().equals("getClass")) {
                        methods.addElement(m);
                    }
                }

            } catch (Exception ex) {
                //Logger.getLogger(Equips.class.getName()).log(Level.SEVERE, null, ex);
            }

            for (Object m : resultSet) {
                Vector row = new Vector(ncamps);

                for (Method mD : methods) {
                    try {
                        row.addElement(mD.invoke(m));
                    } catch (IllegalAccessException ex) {
//                        Logger.getLogger(Equips.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
//                        Logger.getLogger(Equips.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
//                        Logger.getLogger(Equips.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

//               for(Field f:classe.getDeclaredFields())
//                    try {
//                        row.addElement(f.get(m));
//                   } catch (IllegalArgumentException ex) {
//                       Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
//                   } catch (IllegalAccessException ex) {
//                       Logger.getLogger(VistaActors.class.getName()).log(Level.SEVERE, null, ex);
//                   }
                data.addElement(row);
            }
        }

        model = new DefaultTableModel(data, columnNames);
        taula.setModel(model);

        TableColumn column;
        for (int i = 0; i < taula.getColumnCount(); i++) {
            column = taula.getColumnModel().getColumn(i);
            column.setMaxWidth(250);
        }
    }

    public static class OrdenarMetodeClasseAlfabeticament implements Comparator {

        public int compare(Object o1, Object o2) {

            Method mo1 = ((PropertyDescriptor) o1).getReadMethod();
            Method mo2 = ((PropertyDescriptor) o2).getReadMethod();

            if (mo1 != null && mo2 != null) {
                return (int) mo1.getName().compareToIgnoreCase(mo2.getName());
            }

            if (mo1 == null) {
                return -1;

            } else {
                return 1;
            }
        }
    }

    public static class OrdenarCampClasseAlfabeticament implements Comparator {

        public int compare(Object o1, Object o2) {
            return (int) (((Field) o1).getName().compareToIgnoreCase(((Field) o2).getName()));
        }
    }
    
}