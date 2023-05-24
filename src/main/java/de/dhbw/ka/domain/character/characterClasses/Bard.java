package de.dhbw.ka.domain.character.characterClasses;

import de.dhbw.ka.domain.character.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Bard extends CharacterClass {

    private final String[] featuresByLevel = new String[]{
            "Spellcasting, Bardic Inspiration (d6)",
            "Jack of All Trades, Song of Rest (d6)",
            "Bard College, Expertise",
            "Ability Score Improvement",
            "Bardic Inspiration (d8), Font of Inspiration",
            "Countercharm, Bard College feature",
            "-",
            "Ability Score Improvement",
            "Song of Rest (d8)",
            "Bardic Inspiration (d10), Expertise, Magical Secrets",
            "-",
            "Ability Score Improvement",
            "Song of Rest (d10)",
            "Magical Secrets, Bard College feature",
            "Bardic Inspiration (d12)",
            "Ability Score Improvement",
            "Song of Rest (d12)",
            "Magical Secrets",
            "Ability Score Improvement",
            "Superior Inspiration"
    };

    @Override
    public List<AbilityScores> getAbilityProficiencies() {
        return new ArrayList<>(List.of(AbilityScores.DEXTERITY, AbilityScores.CHARISMA));
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
        return "Bard";
    }
}
