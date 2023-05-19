package de.dhbw.ka.test.rng;

import de.dhbw.ka.adapter.rng.RandomNumberGenerator;
import de.dhbw.ka.adapter.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.adapter.rng.RandomNumberGeneratorImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

    @Test
    public void testGenerate() {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorImplementation();

        for (int i = 0; i < 1000; i++) {
            int randomNum = randomNumberGenerator.generate(1, 10);
            Assertions.assertTrue(randomNum >= 1 && randomNum <= 10);
        }
    }

    @Test
    public void testInterface() {
        RandomNumberGeneratorAdapter randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());

        for (int i = 0; i < 100; i++) {
            int randomNum = randomNumberGenerator.requestRandomNumber(1, 4);
            Assertions.assertTrue(randomNum >= 1 && randomNum <= 4);
        }
    }
}
