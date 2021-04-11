package mvcejemplocompleto;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static mvcejemplocompleto.Model.CANVAS_ALTO;
import static mvcejemplocompleto.Model.CANVAS_ANCHO;

/**
 * Controlador del modelo.
 * Muestra por pantalla ambas vistas del modelo, a la par que gestiona los
 * botones que permiten desplazarla (tanto los de teclado como los de la ventana).
 * @author Alejandro Barrio
 */
public class Controlador extends JFrame {
    
    private Model model = new Model();
    private Vista1 vista1 = new Vista1(model);
    private Vista2 vista2 = new Vista2(model);
    
    public void configuraControlador(){
        // Configuro botón izquierdo
        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnIzq = new JButton("Mover Izquierda");
        btnIzq.setFont(new Font("Arial", Font.PLAIN, Math.round(CANVAS_ANCHO/30)));
        btnIzq.setToolTipText("Desplaza la línea hacia la izquierda");
        btnPanel.add(btnIzq);
        btnIzq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.izq();
                vista1.repaint();
                requestFocus();
            }
        });
        // Configuro botón derecho
        JButton btnDer = new JButton("Mover Derecha");
        btnDer.setFont(new Font("Arial", Font.PLAIN, Math.round(CANVAS_ANCHO/30)));
        btnDer.setToolTipText("Desplaza la línea hacia la derecha");
        btnPanel.add(btnDer);
        btnDer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.dcha();
                vista1.repaint();
                requestFocus();
            }
        });
        // Añadimos elementos al container
        vista1.setPreferredSize(new Dimension(CANVAS_ANCHO, CANVAS_ALTO));
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(vista1, BorderLayout.CENTER);
        cp.add(btnPanel, BorderLayout.SOUTH);
        cp.add(BorderLayout.NORTH,vista2);
        model.addObserver(vista1);
        model.addObserver(vista2);
        // Getión del input de teclado
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                switch(evt.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        model.izq();
                        vista1.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        model.dcha();
                        vista1.repaint();
                        break;
                }
            }
        });
        // Visualización por pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MVC Mover una Linea");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        requestFocus();
    }
}