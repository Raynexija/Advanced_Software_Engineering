package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.List;

public class Dwarf implements Race {

    private final List<String> features = List.of("Darkvision", "Dwarven Resilience", "Dwarven Combat Training", "Stonecunning");

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(CONSTITUTION)) {
            return 2;
        } else return 0;
    }

    @Override
    public int getWalkingSpeed() {
        return 25;
    }

    @Override
    public List<String> getFeatures() {
        return features;
    }
}
