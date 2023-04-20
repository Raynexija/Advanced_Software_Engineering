package de.dhbw.ka.races;

import de.dhbw.ka.AbilityScores;

import java.util.List;

public class Dwarf implements Race {
    public int getRacialBonus(AbilityScores abilityScore) {
        switch (abilityScore) {
            case CONSTITUTION:
                return 2;
            default:
                return 0;
        }
    }

    public int getWalkingSpeed() {
        return 25;
    }

    @Override
    public List<String> getRacialTraits() {
        return null;
    }
}