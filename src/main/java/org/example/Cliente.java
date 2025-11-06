package org.example;

import java.util.Random;

public class Cliente extends Thread {
    private final Restaurante restaurante;
    private int mesaAsignada;

    public Cliente(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public void run() {
        Random random = new Random();

        // asignar mesa
        mesaAsignada = restaurante.asignarMesa(getName());

        // tiempo comiendo
        try {
            Thread.sleep(random.nextInt(1000, 3001));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        // dejar mesa
        restaurante.dejarMesa(mesaAsignada);
    }
}
