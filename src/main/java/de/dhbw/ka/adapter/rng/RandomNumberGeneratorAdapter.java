package de.dhbw.ka.adapter.rng;

import de.dhbw.ka.application.interfaces.RandomNumberService;

public class RandomNumberGeneratorAdapter implements RandomNumberService {
    private final RandomNumberGenerator randomNumberGenerator;

    public RandomNumberGeneratorAdapter(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public int requestRandomNumber(int min, int max) {
        return randomNumberGenerator.generate(min, max);
    }
}
