package de.dhbw.ka.characterClasses;

import de.dhbw.ka.AbilityScores;

public class Fighter implements CharacterClass {
    @Override
    public boolean isProficient(AbilityScores abilityScore) {
        if (abilityScore == AbilityScores.STRENGTH || abilityScore == AbilityScores.CONSTITUTION)
            return true;
        else
            return false;
    }
}
