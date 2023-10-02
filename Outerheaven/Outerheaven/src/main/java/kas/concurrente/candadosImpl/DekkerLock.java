package kas.concurrente.candadosImpl;

import kas.concurrente.candados.Lock;

public class DekkerLock implements Lock{

    //arreglo de booleanos para saber si los hilos quieren entrar en la seccion critica
    private volatile boolean[] flag = new boolean[2]; //tamano dos porque dos hilos
    private volatile int victima; 

    @Override
    public void lock() {
        //hilo1
        int id = Integer.valueOf(Thread.currentThread().getName()) % 2;
        
        //el id del segundo hilo
        int id2 = 1 - id; 
        //si el hilo1 es 0, el hilo2 es 1
        //si el hilo1 es 1, el hilo2 es 0
        
        flag[id] = true; //la bandera del hilo1 es cambiada a true, en el arreglo 
        //hilo1 quiere entrar a la seccion critica

        while(flag[id2]){ //el otro hilo tambien quiere entrar a la seccion critica
            if(victima == id2){                                 
                flag[id] = false; //hilo1 le da su turno al otro hilo

                while(victima == id2); 
                flag[id] = true; //hilo1 quiere entrar a la CS
            }
        }
    }

    @Override
    public void unlock() {
        int id= Integer.valueOf(Thread.currentThread().getName()) % 2;
        flag[id] = false; //va a ceder su turno a hilo2
        victima = 1 - id; //el otro hilo pasa a ser la victima ahora
    } 
}