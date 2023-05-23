package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.List;

public class HalfOrc implements Race {

    private final List<String> features = List.of("Darkvision", "Menacing", "Relentless Endurance", "Savage Attacks");

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(STRENGTH)) {
            return 2;
        } else if (abilityScore.equals(CONSTITUTION)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getWalkingSpeed() {
        return 30;
    }

    @Override
    public List<String> getFeatures() {
        return features;
    }
}
