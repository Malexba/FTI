/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jose Vicente Alvarez
 */
public class PruebaReproductor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater( new Runnable(){
            public void run(){
                new Reproductor();
            }
        });
        
    }
    
}
