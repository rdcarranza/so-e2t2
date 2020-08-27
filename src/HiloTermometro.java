import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HiloTermometro extends Thread{
    Termometro termometro;
    BufferedInputStream buffer;
    AudioInputStream ais;
    Clip sonido;

    public HiloTermometro(Termometro t){
        termometro=t;

        try {
            ClassLoader classLoader = this.getClass().getClassLoader();

            InputStream input=classLoader.getResourceAsStream("sonidos/alarma.wav");

            buffer = new BufferedInputStream(input);

            if(input!=null) {
                ais = AudioSystem.getAudioInputStream(buffer);
            }else {
                File archivo=new File("sonidos/alarma.wav");
                ais = AudioSystem.getAudioInputStream(archivo);
            }

            /*
            File archivo=new File("sonidos/alarma.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(archivo);
            */

            AudioFormat formato=ais.getFormat();
            DataLine.Info info= new DataLine.Info(Clip.class,formato);

            sonido= (Clip) AudioSystem.getLine((Line.Info) info);
            sonido.open(ais);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            sonido=null;
        } catch (NullPointerException e){
            e.printStackTrace();
            sonido=null;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            sonido=null;
        } catch (IOException e) {
            e.printStackTrace();
            sonido=null;
        }
    }
    @Override
    public void run() {
        super.run();

        while (true){
            String res=termometro.sensar();
            System.out.println("Sensor: "+res);
            java.awt.Toolkit.getDefaultToolkit().beep();

            try {
                if(termometro.getTemperatura()>50){
                    System.out.println("ALARMA! temperatura alta!");
                    if(sonido!=null){

                        sonido.loop(1);
                        Thread.sleep(3000);
                        sonido.stop();
                    }
                }
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
