package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.ArrayList;
import java.util.List;

public class Tiefling implements Race {

    private List<String> features = new ArrayList<>(List.of(new String[]{"Darkvision", "Hellish Resistance", "Infernal Legacy"}));

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(CHARISMA)) {
            return 2;
        } else if (abilityScore.equals(INTELLIGENCE)) {
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
