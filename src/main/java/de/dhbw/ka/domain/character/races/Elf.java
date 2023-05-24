package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.List;

public class Elf implements Race {

    private final List<String> features = List.of("Darkvision", "Keen Senses", "Fey Ancestry", "Trance");

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(DEXTERITY)) {
            return 2;
        } else return 0;
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
        return "Elf";
    }
}
