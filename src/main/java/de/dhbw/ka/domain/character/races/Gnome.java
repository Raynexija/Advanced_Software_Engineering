package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.ArrayList;
import java.util.List;

public class Gnome implements Race {

    private List<String> features = new ArrayList<>(List.of(new String[]{"Darkvision", "Gnome Cunning"}));

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(INTELLIGENCE)) {
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
