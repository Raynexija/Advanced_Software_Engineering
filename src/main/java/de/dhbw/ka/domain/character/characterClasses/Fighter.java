package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import static de.dhbw.ka.domain.character.AbilityScores.*;

import java.util.ArrayList;
import java.util.List;

public class Fighter extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Fighting Style, Second Wind",
            "Action Surge (one use)",
            "Martial Archetype",
            "Ability Score Improvement",
            "Extra Attack",
            "Ability Score Improvement",
            "Martial Archetype Feature",
            "Ability Score Improvement",
            "Indomitable (one use)",
            "Martial Archetype Feature",
            "Extra Attack (2)",
            "Ability Score Improvement",
            "Indomitable (two uses)",
            "Ability Score Improvement",
            "Martial Archetype Feature",
            "Ability Score Improvement",
            "Action Surge (two uses), Indomitable (three uses)",
            "Martial Archetype Feature",
            "Ability Score Improvement",
            "Extra Attack (3)"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(STRENGTH, CONSTITUTION));
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
        return "Fighter";
    }
}
