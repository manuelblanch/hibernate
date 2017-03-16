/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "consoles")
public class Consola {

    public Consola(String _2_nom, String _3_marca) {
        this._2_nom = _2_nom;
        this._3_marca = _3_marca;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _1_id;
    
    private String _2_nom;
    
    private String _3_marca;
    
    @Transient
    private Consola _4_competencia;

    public Consola() {
       
    }

    public long get1_id() {
        return _1_id;
    }

    private void set1_id(long _1_id) {
        this._1_id = _1_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_marca() {
        return _3_marca;
    }

    public void set3_marca(String _3_marca) {
        this._3_marca = _3_marca;
    }

    public Consola get4_competencia() {
        return _4_competencia;
    }

    public void set4_competencia(Consola _4_competencia) {
        this._4_competencia = _4_competencia;
    }

 
}
