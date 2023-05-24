package de.dhbw.ka.domain.character.races;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.List;

public class HalfElf implements Race {

    private final List<String> features = List.of("Darkvision", "Fey Ancestry", "Skill Versatility");
    private final AbilityScores[] abilityScorePlusOne = new AbilityScores[2];

    public static AbilityScores getAlreadyIncreasedAbility() {
        return CHARISMA;
    }

    public HalfElf(AbilityScores[] abilityScorePlusOne) {
        this.abilityScorePlusOne[0] = abilityScorePlusOne[0];
        this.abilityScorePlusOne[1] = abilityScorePlusOne[1];
    }

    @Override
    public int getRacialBonus(AbilityScores abilityScore) {
        if (abilityScore.equals(CHARISMA)) {
            return 2;
        } else if (abilityScore.equals(abilityScorePlusOne[0]) || abilityScore.equals(abilityScorePlusOne[1])) {
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

    @Override
    public String getRaceName() {
        return "Half-Elf";
    }
}
