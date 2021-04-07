package reproductor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import javax.sound.sampled.Clip;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Clase que representa a la ventana principal.
 * Reproductor de archivos de audio de extensión ".wav". Además de los comandos
 * que permiten controlar la reproducción del archivo, indica al usuario el
 * nombre del archivo que se está reproduciendo.
 * @author Alejandro Barrio
 */
public class Reproductor extends JFrame implements ChangeListener{
    
    public final static int w = escalado(true);
    public static Barra s;
    public static JButton abrir, reproducir, stop, pausa, skip;
    public static boolean skipeado;
    public static JLabel etiqueta, cancion;
    public static Clip clip;
    public static File audio;
    public static Thread t;
    public static long tMax,tActual;
    public static Double tMaxFormato;
    private final DecimalFormat df = new DecimalFormat("0.0");
    
    public Reproductor(){  
        int i=1;
        UIManager.LookAndFeelInfo looks[];
        looks = UIManager.getInstalledLookAndFeels();
        try{
            UIManager.setLookAndFeel(looks[i].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch(Exception e) {}
        setTitle("Reproductor WAV");
        setLayout(new BorderLayout());
        cancion = new JLabel("Actualmente no se está reproduciendo ningún archivo", JLabel.CENTER);
        cancion.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancion.setFont(new Font("Arial", Font.PLAIN, Math.round(w/70))); // Definimos el formato del texto de la etiqueta
        add(cancion, BorderLayout.CENTER);
        JPanel comandos = new JPanel( new FlowLayout());
        s = new Barra(SwingConstants.HORIZONTAL,0,100,0);
        etiqueta = new JLabel("              0");
        etiqueta.setFont(new Font("Arial", Font.BOLD, Math.round(w/70))); // Definimos el formato del texto de la etiqueta
        etiqueta.setForeground(Color.RED); // Cambiamos color del texto
        abrir = new Boton("abrir","../icon/abrir.png",0);
        comandos.add(abrir);
        reproducir = new Boton("play","/icon/play.png",1);
        comandos.add(reproducir);
        stop = new Boton("stop","/icon/stop.png",2);
        pausa = new Boton("pausa","/icon/pausa.png",3);
        comandos.add(pausa);
        comandos.add(stop);
        skip = new Boton("skip","/icon/skip.png",4);
        comandos.add(skip);
        comandos.add(s);
        comandos.add(etiqueta);
        add(comandos, BorderLayout.SOUTH);
        s.addChangeListener((ChangeListener) this);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    // Función para obtener ancho y alto de la consola. Devuelve ancho o alto en función del booleano
    private static int escalado(Boolean b) {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension sPantalla = t.getScreenSize();
        if (b) { // Ancho
            int wPantalla = (int)((sPantalla.getWidth()));
            return wPantalla;
        } else { // Alto
            int hPantalla = (int)((sPantalla.getHeight()));
            return hPantalla;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) { // Permite actualizar la etiqueta en función del JSlider
        etiqueta.setText(df.format(tMaxFormato-(tMaxFormato*s.getValue()/100))+" seg");
    }
}