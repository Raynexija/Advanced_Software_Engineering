package de.dhbw.ka.characterClasses;

import de.dhbw.ka.AbilityScores;

import java.util.List;

public abstract class CharacterClass {

    private int[] proficiencyBonus = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};
    protected String hitDice;

    public abstract List<AbilityScores> getAbilityProficiencies();

    public int getProficiencyBonus(int level) {
        return proficiencyBonus[level - 1];
    }
}
