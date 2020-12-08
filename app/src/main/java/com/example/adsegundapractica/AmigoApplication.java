package com.example.adsegundapractica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AmigoApplication {

    private static final int NUMERO_DE_HILOS = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService EjecutorDeHilos =
            Executors.newFixedThreadPool(NUMERO_DE_HILOS);

}
