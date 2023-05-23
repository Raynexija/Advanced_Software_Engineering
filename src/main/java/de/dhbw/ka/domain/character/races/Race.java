package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.List;

public interface Race {
    int getRacialBonus(AbilityScores abilityScore);

    int getWalkingSpeed();

    List<String> getFeatures();
}
