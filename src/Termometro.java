import java.util.Observable;
import java.util.Random;

public class Termometro extends Observable {
    float temperatura;
    String escala;

    public Termometro(String e){
        temperatura=0;
        escala=e;
    }

    public String sensar(){
        Random r=new Random();
        int pEntera=100;
        int signo=r.nextInt();
        if(signo<0){
            pEntera=((int)(r.nextFloat()*100))*(-1);
        }else {
            pEntera = ((int) (r.nextFloat() * 100));
        }

        float pDecimal= (float)((int)(r.nextFloat()*1000))/1000;
        temperatura=pEntera+pDecimal;
        return temperatura+"° "+escala;
    }
    public float getTemperatura(){
        return temperatura;
    }
    public String getEscala(){
        return escala;
    }

    private void actualizar(){
        this.setChanged();
        this.notifyObservers();
        this.clearChanged();
    }
}
