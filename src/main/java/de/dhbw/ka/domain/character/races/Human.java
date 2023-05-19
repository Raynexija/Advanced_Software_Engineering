package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

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
