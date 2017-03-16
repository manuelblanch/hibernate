/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import java.util.List;
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
@Table(name = "accesoris")
public class Accesoris {

    public Accesoris(long id, String nom, String tipus, String preu, List<Consola>Consoles) {
        this.id = id;
        this.nom = nom;
        this.tipus = tipus;
        this.preu = preu;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nom;
    
    @Column(name = "tipus")// ens guarda la columna amb el nom indicat
    private String tipus;
    
    @Transient// per a no guardar
    private String preu;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getPreu() {
        return preu;
    }

    public void setPreu(String preu) {
        this.preu = preu;
    }

}
