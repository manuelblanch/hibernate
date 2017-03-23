/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author manuel
 */
@Entity
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;

    @OneToOne(mappedBy = "competencia", optional = true)
    private Consola competenciaDe;

    public Competencia(long id, String nom, Consola competenciaDe) {
        this.id = id;
        this.nom = nom;
        this.competenciaDe = competenciaDe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Consola getCompetenciaDe() {
        return competenciaDe;
    }

    public void setCompetenciaDe(Consola competenciaDe) {
        this.competenciaDe = competenciaDe;
    }
    
    
}


