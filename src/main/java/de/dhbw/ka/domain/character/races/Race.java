package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

public interface Race {
    public int getRacialBonus(AbilityScores abilityScore);

    public int getWalkingSpeed();

}
