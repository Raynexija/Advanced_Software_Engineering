package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Barbarian extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Rage, Unarmored Defense",
            "Reckless Attack, Danger Sense",
            "Primal Path",
            "Ability Score Improvement",
            "Extra Attack, Fast Movement",
            "Path Feature",
            "Feral Instinct",
            "Ability Score Improvement",
            "Brutal Critical(1 die)",
            "Path Feature",
            "Relentless Rage",
            "Ability Score Improvement",
            "Brutal Critical(2 dice)",
            "Path Feature",
            "Persistent Rage",
            "Ability Score Improvement",
            "Brutal Critical(3 dice)",
            "Indomitable Might",
            "Ability Score Improvement",
            "Primal Champion"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.STRENGTH, AbilityScores.CONSTITUTION));
    }

    @Override
    public String getHitDice() {
        return "d12";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }
}
