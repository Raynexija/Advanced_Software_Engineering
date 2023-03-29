package de.dhbw.ka.characterClasses;

import de.dhbw.ka.AbilityScores;

import java.util.ArrayList;
import java.util.List;

public class Fighter extends CharacterClass {
    protected String hitDice= "1d10";

    @Override
    public List<AbilityScores> getAbilityProficiencies () {
        List<AbilityScores> proficiencies = new ArrayList<>();
        proficiencies.add(AbilityScores.STRENGTH);
        proficiencies.add(AbilityScores.CONSTITUTION);
        return proficiencies;
    }
}
