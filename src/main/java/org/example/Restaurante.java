package org.example;

public class Restaurante {
    private final int AFORO = 30;
    private final String[] mesas = new String[AFORO];


    public int asignarMesa(){
        int mesa;
        while ((mesa = encontrarMesa()) == -1){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        return 0;
    }

    private int encontrarMesa(){
        for (int i = 0; i < AFORO; i++) {
            if (mesas[i] == null){
                return i;
            }
        }
        return -1;
    }
}
