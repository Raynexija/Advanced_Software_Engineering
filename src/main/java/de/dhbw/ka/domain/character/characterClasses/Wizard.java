package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Spellcasting, Arcane Recovery",
            "Arcane Tradition",
            "-",
            "Ability Score Improvement",
            "-",
            "Arcane Tradition feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Arcane Tradition feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Arcane Tradition feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Spell Mastery",
            "Ability Score Improvement",
            "Signature Spell"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.INTELLIGENCE, AbilityScores.WISDOM));
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
        return "Wizard";
    }
}
