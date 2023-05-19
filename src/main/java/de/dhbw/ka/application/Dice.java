package de.dhbw.ka.application;

import de.dhbw.ka.application.interfaces.RandomNumberService;

public class Dice {

    private RandomNumberService randomNumberGenerator;

    public Dice(RandomNumberService randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public enum types {
        D4, D6, D8, D10, D12, D20, D100
    }

    /**
     * Rolls a die with the given max value
     *
     * @param max the maximum value of the die (inclusive)
     * @return a random number between 1 and the given max value
     */
    public int roll(int max) {
        return randomNumberGenerator.requestRandomNumber(1, max);
    }

    /**
     * Rolls a 4-sided die
     *
     * @return a random number between 1 and 4
     */
    public int rollD4() {
        return roll(4);
    }

    /**
     * Rolls a 6-sided die
     *
     * @return a random number between 1 and 6
     */
    public int rollD6() {
        return roll(6);
    }

    /**
     * Rolls a 8-sided die
     *
     * @return a random number between 1 and 8
     */
    public int rollD8() {
        return roll(8);
    }

    /**
     * Rolls a 10-sided die
     *
     * @return a random number between 1 and 10
     */
    public int rollD10() {
        return roll(10);
    }

    /**
     * Rolls a 12-sided die
     *
     * @return a random number between 1 and 12
     */
    public int rollD12() {
        return roll(12);
    }

    /**
     * Rolls a 20-sided die
     *
     * @return a random number between 1 and 20
     */
    public int rollD20() {
        return roll(20);
    }

    /**
     * Rolls a 100-sided die
     *
     * @return a random number between 1 and 100
     */
    public int rollD100() {
        return roll(100);
    }
}
