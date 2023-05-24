package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Rogue extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Expertise, Sneak Attack, Thieves' Cant",
            "Cunning Action",
            "Roguish Archetype",
            "Ability Score Improvement",
            "Uncanny Dodge",
            "Expertise",
            "Evasion",
            "Ability Score Improvement",
            "Roguish Archetype feature",
            "Ability Score Improvement",
            "Reliable Talent",
            "Ability Score Improvement",
            "Roguish Archetype feature",
            "Blindsense",
            "Slippery Mind",
            "Ability Score Improvement",
            "Roguish Archetype feature",
            "Elusive",
            "Ability Score Improvement",
            "Stroke of Luck"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.DEXTERITY, AbilityScores.INTELLIGENCE));
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
        return "Rogue";
    }
}
