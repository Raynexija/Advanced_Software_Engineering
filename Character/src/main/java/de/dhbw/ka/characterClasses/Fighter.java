package de.dhbw.ka.characterClasses;

import de.dhbw.ka.AbilityScores;

public class Fighter extends CharacterClass {
    protected String hitDice= "1d10";
    @Override
    public boolean isProficient(AbilityScores abilityScore) {
        if (abilityScore == AbilityScores.STRENGTH || abilityScore == AbilityScores.CONSTITUTION)
            return true;
        else
            return false;
    }
}
