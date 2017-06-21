package view;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Tuile;
import model.aventuriers.Aventurier;
import util.Observateur;
import static util.Utils.EtatTuile.ASSECHEE;
import static util.Utils.EtatTuile.INONDEE;

public class VueTuile extends JButton{
    
    private Color couleur;
    private int colonne;
    private int ligne;
    
    
    VueTuile(Tuile tuile){
        ligne= tuile.getLigne();
        colonne=tuile.getColonne();
        JPanel panel = new JPanel(new GridLayout(4,1));
        JLabel label = new JLabel(tuile.getNom());
        this.setLayout(new BorderLayout());
        this.add(label,BorderLayout.NORTH);
        if (tuile.getEtatTuile()==ASSECHEE){
            setCouleur(Color.orange);
        } else if (tuile.getEtatTuile()==INONDEE){
            setCouleur(Color.BLUE);
        } else {
            setCouleur(Color.gray);
            this.setEnabled(false);
        }
        panel.setBackground(couleur);
        this.setBackground(couleur);
        HashMap<String,Aventurier> aventuriers = tuile.getAventurierPresent();
        for (Aventurier ave: aventuriers.values()){
            JPanel joueur = new JPanel();
            if (ave!=null){
                joueur.setBackground(ave.getCouleur());
                
            } else {
                joueur.setBackground(couleur);
            }
            panel.add(joueur);
        }
        
        this.add(panel, BorderLayout.CENTER);
    }  

    public int getColonne() {
        return colonne;
    }

    public int getLigne() {
        return ligne;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        
        this.couleur = couleur;
        repaint();
        System.out.println("couleur");
    }

    
    
    
}