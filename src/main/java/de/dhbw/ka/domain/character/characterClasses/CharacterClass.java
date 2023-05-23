package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.List;

public abstract class CharacterClass {

    private final int[] proficiencyBonus = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6};

    public abstract List<AbilityScores> getAbilityProficiencies();

    public int getProficiencyBonus(int level) {
        return proficiencyBonus[level - 1];
    }

    public abstract String getHitDice();

    public abstract List<String> getFeatures(int level);
}
