/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.aventuriers;

import java.awt.Color;
import model.Tuile;

/**
 *
 * @author Léa
 */
public class Ingenieur extends Aventurier{
    
    public Ingenieur(String nom, String role,Tuile position) {
        super(nom, role,position);
        super.setCouleur(Color.red);
    }
    
}
