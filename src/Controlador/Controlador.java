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

import Model.ConsolaDAO;
import Model.Consola1to1DAO;
import Model.Model;
import Vista.Vista;
import entitats.Accesoris;
import entitats.Consola;
import entitats.Jocs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Controlador {

    //private ConsolaDAO model;
    private Model model;
    private Vista vista;
    private TableColumn objecte;
    private TableColumn objecte2;
    private int id = -1;
    private int filasel = -1;
    private String nom = "";
    private String marca = "";
    private String competencia = "";

    public Controlador(Model model, Vista vista) {

        //this.model = model;
        this.model = model;
        this.vista = vista;
        vista.setVisible(true);
        tancaraplicacio();
        insertar();
        modificar();
        eliminar();
        clictaula();
        clictaula2();
      
        insertaraccesori();
        modificaraccesori();
        eliminaraccesori();
        
        objecte = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjTable1(), Consola.class);
        objecte2 = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjTable2(), Accesoris.class);
        
        carregaCombo((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjComboBox1());
        carregaCombo((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjComboBox2());
        
    }

    public void insertar() {

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (e.getSource().equals(vista.getjButton2())) {
                    if (!vista.getjTextField1().getText().trim().equals("") || !vista.getjTextField2().getText().trim().equals("")) {
                        model.getClasseDAOConsoles().obtenLlista();
                        Consola c = new Consola(vista.getjTextField1().getText(), vista.getjTextField2().getText(), (Consola) vista.getjComboBox1().getSelectedItem());
                        model.getClasseDAOConsoles().guarda(c);
                        objecte = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjTable1(), Consola.class);
                        carregaCombo((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjComboBox1());
                    } else {
                        JOptionPane.showMessageDialog(null, "Es necessita algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }

        };
        vista.getjButton2().addActionListener(actionListener);
    }

    public void modificar() {

        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //DefaultTableModel model2 = (DefaultTableModel) vista.getjTable1().getModel();

                TableColumnModel tcm = (TableColumnModel) vista.getjTable1().getColumnModel();
                if (vista.getjTable1().getSelectedRow() != -1) {
                    vista.getjTable1().addColumn(objecte);
                    DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();
                    Consola modificat = (Consola) modelo.getValueAt(vista.getjTable1().getSelectedRow(), modelo.getColumnCount() - 1);
                    modificat.set2_nom(vista.getjTextField1().getText());
                    modificat.set3_marca(vista.getjTextField2().getText());
                    modificat.set4_competencia((Consola) vista.getjComboBox1().getSelectedItem());
                    vista.getjTable1().removeColumn(objecte);
                    model.getClasseDAOConsoles().actualitza(modificat);
                    vista.getjTable1().addColumn(objecte);
                    objecte = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjTable1(), Consola.class);
                    carregaCombo((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjComboBox1());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        };
        vista.getjButton3().addActionListener(actionListener);
    }

    public void eliminar() {
        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //DefaultTableModel model2 = (DefaultTableModel) vista.getjTable1().getModel();

                TableColumnModel tcm = (TableColumnModel) vista.getjTable1().getColumnModel();
                if (vista.getjTable1().getSelectedRow() != -1) {
                    DefaultTableModel modelo = (DefaultTableModel) vista.getjTable1().getModel();
                    Consola borrat = (Consola) modelo.getValueAt(vista.getjTable1().getSelectedRow(), modelo.getColumnCount() - 1);
                    vista.getjTable1().removeColumn(objecte);
                    model.getClasseDAOConsoles().elimina(borrat);
                    //model.obtenLlistaConsoles().remove(borrat);
                    vista.getjTable1().addColumn(objecte);

                    objecte = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjTable1(), Consola.class);
                    carregaCombo((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjComboBox1());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        };
        vista.getjButton4().addActionListener(actionListener);
    }

    private void insertaraccesori() {

        {

            ActionListener actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    if (e.getSource().equals(vista.getjButton5())) {
                        if (!vista.getjTextField3().getText().trim().equals("") || !vista.getjTextField4().getText().trim().equals("")) {
                            model.getClasseDAOAccesoris().obtenLlista();
                            Accesoris a = new Accesoris(vista.getjTextField3().getText(), vista.getjTextField4().getText(), (Accesoris) vista.getjComboBox2().getSelectedItem());
                            model.getClasseDAOAccesoris().guarda(a);
                            objecte2 = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjTable2(), Accesoris.class);
                            carregaCombo((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjComboBox2());
                        } else {
                            JOptionPane.showMessageDialog(null, "Es necessita algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }

            };
            vista.getjButton5().addActionListener(actionListener);
        }

    }

    public void modificaraccesori() {

        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //DefaultTableModel model2 = (DefaultTableModel) vista.getjTable1().getModel();

                TableColumnModel tcm = (TableColumnModel) vista.getjTable2().getColumnModel();
                if (vista.getjTable2().getSelectedRow() != -1) {
                    vista.getjTable2().addColumn(objecte2);
                    DefaultTableModel modelo = (DefaultTableModel) vista.getjTable2().getModel();
                    Accesoris modificat = (Accesoris) modelo.getValueAt(vista.getjTable2().getSelectedRow(), modelo.getColumnCount() - 1);
                    modificat.set2_nom(vista.getjTextField3().getText());
                    modificat.set3_preu(vista.getjTextField4().getText());
                    modificat.set4_consola((Accesoris) vista.getjComboBox2().getSelectedItem());
                    vista.getjTable2().removeColumn(objecte2);
                    model.getClasseDAOAccesoris().actualitza(modificat);
                    vista.getjTable2().addColumn(objecte2);
                    objecte2 = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjTable2(), Accesoris.class);
                    carregaCombo((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjComboBox2());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        };
        vista.getjButton6().addActionListener(actionListener);
    }

    public void eliminaraccesori() {
        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //DefaultTableModel model2 = (DefaultTableModel) vista.getjTable1().getModel();

                TableColumnModel tcm = (TableColumnModel) vista.getjTable2().getColumnModel();
                if (vista.getjTable2().getSelectedRow() != -1) {
                    DefaultTableModel modelo = (DefaultTableModel) vista.getjTable2().getModel();
                    Accesoris borrat = (Accesoris) modelo.getValueAt(vista.getjTable2().getSelectedRow(), modelo.getColumnCount() - 1);
                    vista.getjTable2().removeColumn(objecte2);
                    model.getClasseDAOAccesoris().elimina(borrat);
                  
                    vista.getjTable2().addColumn(objecte2);

                    objecte2 = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjTable2(), Accesoris.class);
                    carregaCombo((ArrayList) model.getClasseDAOAccesoris().obtenLlista(), vista.getjComboBox2());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        };
        vista.getjButton7().addActionListener(actionListener);
    }

    

    public void clictaula() {
        MouseAdapter mouseAdapterJTable = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getjTable1().getSelectedRow() != -1) {
                    super.mouseClicked(e);
                    DefaultTableModel model = (DefaultTableModel) vista.getjTable1().getModel();
                    vista.getjTextField1().setText(model.getValueAt(vista.getjTable1().getSelectedRow(), 1).toString());
                    vista.getjTextField2().setText(model.getValueAt(vista.getjTable1().getSelectedRow(), 2).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

        };
        vista.getjTable1().addMouseListener(mouseAdapterJTable);

    }

    public void clictaula2() {
        MouseAdapter mouseAdapterJTable = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getjTable2().getSelectedRow() != -1) {
                    super.mouseClicked(e);
                    DefaultTableModel model = (DefaultTableModel) vista.getjTable2().getModel();
                    vista.getjTextField3().setText(model.getValueAt(vista.getjTable2().getSelectedRow(), 1).toString());
                    vista.getjTextField4().setText(model.getValueAt(vista.getjTable2().getSelectedRow(), 2).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Has de seleccionar algun valor!!", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }

        };
        vista.getjTable2().addMouseListener(mouseAdapterJTable);

    }

    

    public void tancaraplicacio() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }

        };
        vista.getjButton1().addActionListener(actionListener);
    }

    public void carregaCombo(ArrayList resultSet, JComboBox combo) {
        combo.setModel(new DefaultComboBoxModel((resultSet != null ? resultSet.toArray() : new Object[]{})));
    }

}
