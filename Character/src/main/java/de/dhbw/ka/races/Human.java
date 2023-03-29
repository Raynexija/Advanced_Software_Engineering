package de.dhbw.ka.races;

import de.dhbw.ka.AbilityScores;

public class Human implements Race{
    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        return 1;
    }

    @Override
    public int getWalkingSpeed(){
        return 30;
    }
}
