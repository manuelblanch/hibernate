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
public class Consola1to1 {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String marca;

    @OneToOne(optional=false)   //competencia entre consoles
    private Consola competencia;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Consola getCompetencia() {
        return competencia;
    }

    //Per a que quan posem la cúspide de la Serralada s'actualitze la propietat
    //cuspideDe del Cim ho podem posar a este setter (o al de Cim, però no als 2)
    public void setCompetencia(Consola competencia) {
        this.competencia = competencia;
        
    }

    
}

//@Entity
//public class Competencia {    
//    @Id 
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private long id;
//    private String nom;
//    
//    @OneToOne(mappedBy="cuspide",optional=true) 
//    private Serralada cuspideDe;
//   
//    //getters i setters, constructors...
//}
