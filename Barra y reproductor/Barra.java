package reproductor;

import javax.swing.JSlider;
import static reproductor.Reproductor.*;

/**
 * Clase que representa a la barra de reproducción.
 * Muestra al usuario la evolución de la reproducción del archivo de audio,
 * ofreciéndole además la posibilidad de acceder a cualquier punto del archivo.
 * @author Alejandro Barrio
 */
public class Barra extends JSlider {
    
    private Double difTiempoFormato;
    
    public Barra(int orientation, int min, int max, int value) {
        super(orientation,min,max,value);
        setPaintTicks(true);
        setMajorTickSpacing(10);
        setMinorTickSpacing(5);
        setEnabled(false);
    }
    
    public class EvolucionSlider implements Runnable {
        public void run(){
            long pos = getValue();
            long tstart = tMax*pos/100;
            clip.setMicrosecondPosition(tstart);
            clip.start();
            tActual=tstart;
            try {
                do {
                    int valor = (int)(tActual*100/tMax);
                    setValue(valor);
                    difTiempoFormato=((tMax-tActual)/100000)/10.0;
                    etiqueta.setText(difTiempoFormato+" seg"); 
                    tActual=clip.getMicrosecondPosition();
                } while(clip.isRunning());
                clip.setMicrosecondPosition(0);
                Thread.sleep(100);
                s.setValue(0);
                if (!skipeado) {
                    reproducir.setEnabled(true);
                    etiqueta.setText(tMaxFormato+" seg");
                }
            } catch(InterruptedException e) {
            } finally {
                stop.setEnabled(false);
                pausa.setEnabled(false);
            }
        }
    }
    
    public void play() {
        t = new Thread(new EvolucionSlider());
        t.start();
    }
}