package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Tuile;
import util.Message;
import util.Observateur;
import util.Parameters;
import static util.Utils.Commandes.BOUGER;

/**
 *
 * @author IUT2-Dept Info
 */
public class VuePlateau extends JFrame {
    private VueGrille grille;
    
    private Observateur observateur;
    private JButton deplacer;
    private JButton assecher;
    private JButton finir;
    public VuePlateau(Tuile[][] tuiles){
        
        this.setLocation(180, Parameters.TOP_AUTRES_VUES);
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        plateauCentre(tuiles);
        this.setSize(1200, util.Parameters.HAUTEUR_AUTRES_VUES);
        //this.setUndecorated(Parameters.UNDECORATED);
        this.setResizable(Parameters.RESIZABLE);
        this.setVisible(true);
        
    }
    
    
    private void plateauCentre(Tuile[][] tuiles){
        JPanel panel = new JPanel(new GridLayout(1,3));
        deplacer=new JButton("Déplacer");
        
        assecher=new JButton("assecher");
        finir=new JButton("finir tour");
        panel.add(deplacer);
        panel.add(assecher);
        panel.add(finir);
        this.add(panel,BorderLayout.NORTH);
        grille = new VueGrille(tuiles);
        
        this.add(grille,BorderLayout.CENTER);
        
        
        deplacer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message(BOUGER,null,null,null,null,0,0);
                observateur.traiterMessage(m);
            }
            
        });
        
        for (VueTuile[] vues : grille.getAffichTuile()){
            for (VueTuile vue: vues){
                vue.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = String.valueOf(vue.getLigne())+String.valueOf(vue.getColonne());
                        Message m = new Message(BOUGER,null,null,null,Integer.valueOf(id),0,0);
                        observateur.traiterMessage(m);
                    }
                    
                });
            }
        }
        
        
    }
    
    public void afficherTuilesPossibles(HashSet<Tuile> tuiles){
        for(Tuile tuile: tuiles){
            int i = tuile.getLigne();
            int j = tuile.getColonne();
            this.grille.getAffichTuile()[i][j].setCouleur(Color.RED);
            //this.grille.getAffichTuile()[i][j].repaint();
            grille.repaint();
            System.out.println("passer");
        }
        repaint();
    }

    public void setObservateur(Observateur observateur) {
        this.observateur = observateur;
    }
    
    public void selectionnerDeplacer(){
        assecher.setEnabled(false);
        finir.setEnabled(false);
        this.repaint();
    }
    public void deselectionner(){
        assecher.setEnabled(true);
        finir.setEnabled(true);
        deplacer.setEnabled(true);
        this.repaint();
    }

   
    
}
