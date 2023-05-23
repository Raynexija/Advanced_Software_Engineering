package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Cleric extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Spellcasting, Divine Domain",
            "Channel Divinity (1/rest), Divine Domain feature",
            "-",
            "Ability Score Improvement",
            "Destroy Undead (CR 1/2)",
            "Channel Divinity (2/rest), Divine Domain feature",
            "-",
            "Ability Score Improvement, Destroy Undead (CR 1), Divine Domain feature",
            "-",
            "Divine Intervention",
            "Destroy Undead (CR 2)",
            "Ability Score Improvement",
            "-",
            "Destroy Undead (CR 3)",
            "-",
            "Ability Score Improvement",
            "Destroy Undead (CR 4), Divine Domain feature",
            "Channel Divinity (3/rest)",
            "Ability Score Improvement",
            "Devine Intervention improvement",
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.WISDOM, AbilityScores.CHARISMA));
    }

    @Override
    public String getHitDice() {
        return "d8";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }
}
