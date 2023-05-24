package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Druid extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Spellcasting, Druidic",
            "Wild Shape, Druid Circle",
            "-",
            "Wild Shape improvement, Ability Score Improvement",
            "-",
            "Druid Circle feature",
            "-",
            "Wild Shape improvement, Ability Score Improvement",
            "-",
            "Druid Circle feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Druid Circle feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Timeless Body, Beast Spells",
            "Ability Score Improvement",
            "Archdruid"
    };


    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.INTELLIGENCE, AbilityScores.WISDOM));
    }

    @Override
    public String getHitDice() {
        return "d8";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }

    @Override
    public String getClassName() {
        return "Druid";
    }
}
