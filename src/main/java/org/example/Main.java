package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Restaurante restaurante = new Restaurante();
        int contador = 0;

        Random random = new Random();

        while (true) {

            try {
                int tiempoEspera = random.nextInt(800, 2001);
                int tiempoEsperaSegundos = (tiempoEspera / 1000);
                System.out.println("El siguiente cliente entrará en " + tiempoEsperaSegundos + "segundos");
                Thread.sleep(tiempoEspera);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Cliente c = new Cliente(restaurante);
            c.setName("Cliente " + contador);

            c.start();

            contador++;

        }

    }
}