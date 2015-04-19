/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openqc.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;


/**
 *
 * @author cheichnah
 */
@Entity
public class Etudiant extends User implements Serializable {
    
    @Column
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
     
    

    @Override
    public String toString() {
        return "com.openqc.entities.Etudiant[ id=" +   id + " ]";
    }

}
