package org.example;

public class Restaurante {
    private final int AFORO = 30;
    private final String[] mesas = new String[AFORO];

    private Object lockMesa = new Object();

    public int asignarMesa(String name) {
        int mesa;
        boolean aviso = false; // flag para imprimir “debe esperar” solo una vez

        synchronized (lockMesa) {
            while ((mesa = encontrarMesa()) == -1) {
                if (!aviso) { // si aún no imprimimos el mensaje
                    System.out.println("El " + Thread.currentThread().getName() + " debe esperar");
                    aviso = true; // marcamos que ya se imprimió
                }
                try {
                    lockMesa.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1; // salir si se interrumpe
                }
            }

            mesas[mesa] = name; // asignamos la mesa
            System.out.println("El " + Thread.currentThread().getName() + " se ha sentado en la mesa " + mesa);
            return mesa;
        }
    }


    private int encontrarMesa() {
        for (int i = 0; i < AFORO; i++) {
            if (mesas[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public void dejarMesa(int mesa) {
        synchronized (lockMesa) {
            System.out.println("El " + Thread.currentThread().getName() + " ha salido del restaurante");
            mesas[mesa] = null;
            lockMesa.notifyAll();
        }
    }
}
