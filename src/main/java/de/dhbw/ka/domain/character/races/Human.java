package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Human implements Race {

    private final List<String> features = new ArrayList<>(List.of(new String[]{"Extra Language"}));

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        return 1;
    }

    @Override
    public int getWalkingSpeed() {
        return 30;
    }

    @Override
    public List<String> getFeatures() {
        return features;
    }

    @Override
    public String getRaceName() {
        return "Human";
    }
}
