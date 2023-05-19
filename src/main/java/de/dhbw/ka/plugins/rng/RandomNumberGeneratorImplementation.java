package de.dhbw.ka.plugins.rng;

import de.dhbw.ka.adapter.rng.RandomNumberGenerator;

import java.util.Random;

public class RandomNumberGeneratorImplementation implements RandomNumberGenerator {
    private final Random random;

    public RandomNumberGeneratorImplementation() {
        this.random = new Random();
    }

    @Override
    public int generate(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}