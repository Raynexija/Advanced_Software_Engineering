package de.dhbw.ka;

import de.dhbw.ka.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.rng.RandomNumberGeneratorImplementation;

public class Main {
    public static void main(String[] args) {
        RandomNumberGeneratorAdapter  randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());
        System.out.println(randomNumberGenerator.requestRandomNumber(1, 10));

        for (int i = 0; i < 10; i++) {
            System.out.println(randomNumberGenerator.requestRandomNumber(1, 10));
        }
    }


}