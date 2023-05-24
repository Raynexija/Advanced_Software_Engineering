package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Paladin extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Divine Sense, Lay on Hands",
            "Fighting Style, Spellcasting, Divine Smite",
            "Divine Health, Sacred Oath",
            "Ability Score Improvement",
            "Extra Attack",
            "Aura of Protection",
            "Sacred Oath feature",
            "Ability Score Improvement",
            "-",
            "Aura of Courage",
            "Improved Divine Smite",
            "Ability Score Improvement",
            "-",
            "Cleansing Touch",
            "Sacred Oath feature",
            "Ability Score Improvement",
            "-",
            "Aura improvements",
            "Ability Score Improvement",
            "Sacred Oath feature"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.WISDOM, AbilityScores.CHARISMA));
    }

    @Override
    public String getHitDice() {
        return "d10";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }

    @Override
    public String getClassName() {
        return "Paladin";
    }
}
