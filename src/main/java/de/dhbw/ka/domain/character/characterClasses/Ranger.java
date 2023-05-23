package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Ranger extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Favored Enemy, Natural Explorer",
            "Fighting Style, Spellcasting",
            "Ranger Archetype, Primeval Awareness",
            "Ability Score Improvement",
            "Extra Attack",
            "Favored Enemy and Natural Explorer improvements",
            "Ranger Archetype feature",
            "Ability Score Improvement, Land's Stride",
            "-",
            "Hide in Plain Sight, Natural Explorer improvement",
            "Ranger Archetype feature",
            "Ability Score Improvement",
            "-",
            "Favored Enemy improvement, Vanish",
            "Ranger Archetype feature",
            "Ability Score Improvement",
            "-",
            "Feral Senses",
            "Ability Score Improvement",
            "Foe Slayer"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.STRENGTH, AbilityScores.DEXTERITY));
    }

    @Override
    public String getHitDice() {
        return "d10";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }
}
