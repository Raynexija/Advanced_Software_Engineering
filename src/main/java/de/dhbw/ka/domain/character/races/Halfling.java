package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.List;

public class Halfling implements Race {

    private final List<String> features = List.of("Lucky", "Brave", "Halfling Nimbleness");

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(DEXTERITY)) {
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
