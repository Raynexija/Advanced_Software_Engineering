package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Monk extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Unarmored Defense, Martial Arts",
            "Ki, Unarmored Movement",
            "Monastic Tradition, Deflect Missiles",
            "Ability Score Improvement, Slow Fall",
            "Extra Attack, Stunning Strike",
            "Ki-Empowered Strikes, Monastic Tradition Feature",
            "Evasion, Stillness of Mind",
            "Ability Score Improvement",
            "Unarmored Movement Improvement",
            "Purity of Body",
            "Monastic Tradition Feature",
            "Ability Score Improvement",
            "Tongue of the Sun and Moon",
            "Diamond Soul",
            "Timeless Body",
            "Ability Score Improvement",
            "Monastic Tradition Feature",
            "Empty Body",
            "Ability Score Improvement",
            "Perfect Self"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.STRENGTH, AbilityScores.DEXTERITY));
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
