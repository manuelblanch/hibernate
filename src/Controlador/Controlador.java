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
import entitats.Consola;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
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
        objecte = CarregaTaulaCombo.carregaTaula((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjTable1(), Consola.class);
        carregaCombo((ArrayList) model.getClasseDAOConsoles().obtenLlista(), vista.getjComboBox1());
    }


    public void insertar() {

        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (e.getSource().equals(vista.getjButton2())) {
                    if (!vista.getjTextField1().getText().trim().equals("") || !vista.getjTextField2().getText().trim().equals("")) {
                        model.getClasseDAOConsoles().obtenLlista();
                        Consola c = new Consola(vista.getjTextField1().getText(), vista.getjTextField2().getText(), (Consola)vista.getjComboBox1().getSelectedItem());
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
                    modificat.set4_competencia((Consola)vista.getjComboBox1().getSelectedItem());
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
