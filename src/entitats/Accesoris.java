/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.*;
import entitats.Consola;

@Entity
@Table(name = "accesoris")
public class Accesoris {

    @Id
    @GeneratedValue
    private long _1_id;

    private String _2_nom;
    private String _3_preu;

    @ManyToOne
    @JoinColumn(name = "consola_id")
    private Consola _4_consola;

    public Accesoris(String _2_nom, String _3_preu, Consola _4_consola) {
        this._2_nom = _2_nom;
        this._3_preu = _3_preu;
        this._4_consola = _4_consola;
    }

    public Accesoris() {
    }

    public long get1_id() {
        return _1_id;
    }

    public void set1_id(long _1_id) {
        this._1_id = _1_id;
    }

    public String get2_nom() {
        return _2_nom;
    }

    public void set2_nom(String _2_nom) {
        this._2_nom = _2_nom;
    }

    public String get3_preu() {
        return _3_preu;
    }

    public void set3_preu(String _3_preu) {
        this._3_preu = _3_preu;
    }

    public Consola get4_consola() {
        return _4_consola;
    }

    public void set4_consola(Consola _4_consola) {
        this._4_consola = _4_consola;
    }

}
