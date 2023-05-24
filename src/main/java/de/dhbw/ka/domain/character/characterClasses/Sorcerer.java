package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Sorcerer extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Spellcasting, Sorcerous Origin",
            "Font of Magic",
            "Metamagic",
            "Ability Score Improvement",
            "-",
            "Sorcerous Origin Feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Metamagic",
            "-",
            "Ability Score Improvement",
            "-",
            "Sorcerous Origin Feature",
            "-",
            "Ability Score Improvement",
            "Metamagic",
            "Sorcerous Origin Feature",
            "Ability Score Improvement",
            "Sorcerous Restoration"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.CONSTITUTION, AbilityScores.CHARISMA));
    }

    @Override
    public String getHitDice() {
        return "d6";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }

    @Override
    public String getClassName() {
        return "Sorcerer";
    }
}
