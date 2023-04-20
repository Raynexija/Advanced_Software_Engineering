package de.dhbw.ka.races;

import de.dhbw.ka.AbilityScores;

import java.util.List;

public class Elf implements Race {
    public int getRacialBonus(AbilityScores abilityScore) {
        switch (abilityScore) {
            case DEXTERITY:
                return 2;
            case WISDOM:
                return 1;
            default:
                return 0;
        }
    }

    public int getWalkingSpeed() {
        return 30;
    }

    @Override
    public List<String> getRacialTraits() {
        return null;
    }
}