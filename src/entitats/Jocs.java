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

/**
 *
 * @author manuel
 */
@Entity
@Table(name = "jocs")
public class Jocs {

    public Jocs(long id, String nom, String jugadors) {
        this.id = id;
        this.nom = nom;
        this.jugadors = jugadors;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String nom;
    
    @Column(name = "jugadors")// ens guarda la columna amb el nom indicat
    private String jugadors;
   

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

    public String getJugadors() {
        return jugadors;
    }

    public void setJugadors(String jugadors) {
        this.jugadors = jugadors;
    }

}
