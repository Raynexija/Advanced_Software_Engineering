package de.dhbw.ka.races;

import de.dhbw.ka.AbilityScores;

import java.util.List;

public interface Race {
    public int getRacialBonus(AbilityScores abilityScore);

    public int getWalkingSpeed();

    public List<String> getRacialTraits();
}
