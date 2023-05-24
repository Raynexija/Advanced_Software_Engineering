package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Warlock extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Otherworldly Patron, Pact Magic",
            "Eldritch Invocations",
            "Pact Boon",
            "Ability Score Improvement",
            "-",
            "Otherworldly Patron Feature",
            "-",
            "Ability Score Improvement",
            "-",
            "Otherworldly Patron Feature",
            "Mystic Arcanum (6th level)",
            "Ability Score Improvement",
            "Mystic Arcanum (7th level)",
            "Otherworldly Patron Feature",
            "Mystic Arcanum (8th level)",
            "Ability Score Improvement",
            "Mystic Arcanum (9th level)",
            "-",
            "Ability Score Improvement",
            "Eldritch Master"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.WISDOM, AbilityScores.CHARISMA));
    }

    @Override
    public String getHitDice() {
        return "D8";
    }

    @Override
    public List<String> getFeatures(int level) {
        return new ArrayList<>(List.of(featuresByLevel).subList(0, level));
    }

    @Override
    public String getClassName() {
        return "Warlock";
    }
}
