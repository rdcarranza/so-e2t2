public class Main {
    public static void main(String[] args) {
        Termometro t=new Termometro("Celsius");
        HiloTermometro ht=new HiloTermometro(t);
        ht.start();
    }
}
