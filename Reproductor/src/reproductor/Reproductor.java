
package reproductor;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Reproductor extends JFrame{
    private JSlider s;
    private JButton abrir, reproducir, stop, pausa;
    private JLabel etiqueta;
    private Clip clip;
    private File audio;
    private Thread t;
    private long tMax,tActual;
    private Double tMaxFormato;
    
public Reproductor(){  
        int i=1;
            UIManager.LookAndFeelInfo looks[];
            looks = UIManager.getInstalledLookAndFeels();
            try{
		UIManager.setLookAndFeel(looks[i].getClassName());
		SwingUtilities.updateComponentTreeUI(this);
            }
            catch(Exception e) {
            }        
        setLayout( new FlowLayout());
        s=new JSlider(SwingConstants.HORIZONTAL,0,100, 0);
        s.setPaintTicks(true);
        s.setMajorTickSpacing(10);
        s.setMinorTickSpacing(5);
        s.setEnabled(false);
        etiqueta=new JLabel("              0");
        abrir= new JButton();
        abrir.setToolTipText("abrir");
        abrir.setIcon(new ImageIcon(getClass().getResource("../icon/abrir.png")));
        abrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                        if (clip!= null) clip.stop();
                        JFileChooser file=new JFileChooser();
                        file.showOpenDialog(abrir);
                        audio=file.getSelectedFile();
                        if (audio!=null){
                            try{
                                clip=AudioSystem.getClip();
                                AudioInputStream sound = AudioSystem.getAudioInputStream(audio);
                                clip.open(sound);
                                tMax = clip.getMicrosecondLength();
                                tMaxFormato= (tMax/100000)/10.0;
                                etiqueta.setText(tMaxFormato+" seg");
                                reproducir.setEnabled(true);
                                s.setEnabled(true);}
                            catch (LineUnavailableException ex){
                                    JOptionPane.showMessageDialog(null,"ERROR\n"+ "no hay fichero de audio","alerta",JOptionPane.ERROR_MESSAGE);
                            }catch (UnsupportedAudioFileException ex){
                                    stop.setEnabled(false);
                                    pausa.setEnabled(false);
                                    reproducir.setEnabled(false);
                                    s.setEnabled(false);
                                    etiqueta.setText("              0");
                                    JOptionPane.showMessageDialog(null,"ERROR\n"+ "no hay fichero de audio","alerta",JOptionPane.ERROR_MESSAGE);}
                       
                            catch (IOException ex){
                                  JOptionPane.showMessageDialog(null,"ERROR\n"+ "no hay fichero","alerta",JOptionPane.ERROR_MESSAGE);}
                    }
            }
        }); 
        add(abrir);
        reproducir=new JButton();
        reproducir.setToolTipText("play");
        reproducir.setIcon(new ImageIcon(getClass().getResource("/icon/play.png")));
        reproducir.setEnabled(false);
        reproducir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                    stop.setEnabled(true);
                    pausa.setEnabled(true);
                    reproducir.setEnabled(false);  
                    play();
    }        
        }); 
        stop= new JButton();
        stop.setToolTipText("stop");
        stop.setIcon(new ImageIcon(getClass().getResource("/icon/stop.png")));
        stop.setEnabled(false);
        stop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                        clip.stop();
                        reproducir.setEnabled(true);
                        stop.setEnabled(false);
                        pausa.setEnabled(false);
            }
        });
        pausa= new JButton();
        pausa.setToolTipText("pausa");
        pausa.setIcon(new ImageIcon(getClass().getResource("/icon/pausa.png")));
        pausa.setEnabled(false);
        pausa.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                        reproducir.setEnabled(true);
                        pausa.setEnabled(false);
                        stop.setEnabled(false);
                        tActual= clip.getMicrosecondPosition();
                        Double tActualFormato=(tActual/100000)/10.0;
                        etiqueta.setText(tActualFormato+" seg");
                        clip.stop();
                        t.interrupt();
            }
        });
        add(reproducir);
        add(pausa);
        add(stop);
        add(s);
        add(etiqueta);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

private class EvolucionSlider implements Runnable{
    public void run(){
        long pos=s.getValue();
        long tstart = tMax*pos/100;
        clip.setMicrosecondPosition(tstart);
        clip.start();
        tActual=tstart;
        try{
            do{
                int valor = (int)(tActual*100/tMax);
                s.setValue(valor);
                Double difTiempoFormato=((tMax-tActual)/100000)/10.0;
                etiqueta.setText(difTiempoFormato+" seg"); 
                tActual=clip.getMicrosecondPosition();
           }while(clip.isRunning());
                clip.setMicrosecondPosition(0);
                Thread.sleep(100);
                s.setValue(0);
                reproducir.setEnabled(true);
                etiqueta.setText(tMaxFormato+" seg");
        }catch(InterruptedException e){
        }finally{
            stop.setEnabled(false);
            pausa.setEnabled(false);
        }
    }
       
}   
private void play(){
    t = new Thread(new EvolucionSlider());
    t.start();
    
}


    
}