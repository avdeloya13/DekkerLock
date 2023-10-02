package kas.concurrente;

public class Main implements Runnable {
    
    public static final Integer DISCOS = 10;

    public static final Double OCELOT = (double) (9/10);

    public static final Double BOSS = (double) (8/10);

    private Integer contadorFinal = 0;

    public Integer getContadorFinal() {
        return contadorFinal;
    }

    public void setContadorFinal(Integer contadorFinal) {

        this.contadorFinal = contadorFinal;
 
    }

    @Override
    public void run() {
        for(int balas = 0; balas < DISCOS; ++balas){

            contadorFinal++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main campoDeTiro = new Main();

        Thread ocelot = new Thread(campoDeTiro,"ocelot");

        Thread boss = new Thread(campoDeTiro,"boss");

        ocelot.start();boss.start();

        ocelot.join();boss.join();
    }
}