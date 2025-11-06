package org.example;

public class Restaurante {
    private final int AFORO = 30;
    private final String[] mesas = new String[AFORO];

    private Object lockMesa = new Object();

    public int asignarMesa(String name) {
        int mesa;
        synchronized (lockMesa) {
            while ((mesa = encontrarMesa()) == -1) {
                try {
                    System.out.println("El " + Thread.currentThread().getName() + " debe esperar");
                    lockMesa.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            mesas[mesa] = name;
            System.out.println("El " + Thread.currentThread().getName() + " se ha sentado el la mesa" + mesa);

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
