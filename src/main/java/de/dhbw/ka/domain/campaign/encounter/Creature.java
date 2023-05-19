package de.dhbw.ka.domain.campaign.encounter;


import de.dhbw.ka.domain.character.AbilityScores;

import java.util.HashMap;

import static de.dhbw.ka.domain.character.AbilityScores.*;
import static de.dhbw.ka.domain.character.AbilityScores.CHARISMA;

public class Creature {

    private final String name;
    private final int armorClass;
    private final int hitPoints;
    private final int initiativeModifier;
    private final HashMap<AbilityScores, CreatureAbilityScore> abilityScores = new HashMap<>();

    public Creature(String name, int armorClass, int hitPoints, int initiativeModifier, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.armorClass = armorClass;
        this.hitPoints = hitPoints;
        this.initiativeModifier = initiativeModifier;
        this.abilityScores.put(STRENGTH, new CreatureAbilityScore(strength));
        this.abilityScores.put(DEXTERITY, new CreatureAbilityScore(dexterity));
        this.abilityScores.put(CONSTITUTION, new CreatureAbilityScore(constitution));
        this.abilityScores.put(INTELLIGENCE, new CreatureAbilityScore(intelligence));
        this.abilityScores.put(WISDOM, new CreatureAbilityScore(wisdom));
        this.abilityScores.put(CHARISMA, new CreatureAbilityScore(charisma));
    }

    public record CreatureAbilityScore(int score, int modifier) {
        public CreatureAbilityScore(int score, int modifier) {
            this.score = score;
            this.modifier = calculateModifier(score);
        }

        public CreatureAbilityScore(int score) {
            this(score, 0);
        }

        private static int calculateModifier(int score) {
            if (score >= 10) {
                return (score - 10) / 2;
            } else {
                return (score - 11) / 2;
            }
        }
    }

    public CreatureAbilityScore abilityScore(AbilityScores abilityScore) {
        return abilityScores.get(abilityScore);
    }

    public String name() {
        return name;
    }

    public int armorClass() {
        return armorClass;
    }

    public int hitPoints() {
        return hitPoints;
    }

    public int initiativeModifier() {
        return initiativeModifier;
    }
}
