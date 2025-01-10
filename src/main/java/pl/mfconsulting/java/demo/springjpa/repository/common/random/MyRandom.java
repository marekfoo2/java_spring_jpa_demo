package pl.mfconsulting.java.demo.springjpa.repository.common.random;

import java.util.Random;
import java.util.UUID;

public final class MyRandom {
    private static final Random gen = new Random();

    public static int randInt1000() {
        return gen.nextInt(10000);
    }

    public static String randUUID() {
        return UUID.randomUUID().toString();
    }

    public static String randStr(int n){
        return UUID.randomUUID().toString().substring(0, n);
    }
}
