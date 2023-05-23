package de.dhbw.ka.domain.campaign.encounter;


import de.dhbw.ka.domain.character.AbilityScores;

import java.util.HashMap;

import static de.dhbw.ka.domain.character.AbilityScores.*;

public class Creature {

    private final String name;
    private final int armorClass;
    private final int maxHitPoints;
    private int currentHitPoints;
    private final int initiativeModifier;
    private final HashMap<AbilityScores, CreatureAbilityScore> abilityScores = new HashMap<>();

    public Creature(String name, int armorClass, int maxHitPoints, int initiativeModifier, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.name = name;
        this.armorClass = armorClass;
        this.maxHitPoints = maxHitPoints;
        this.currentHitPoints = maxHitPoints;
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

    private void modifyHitPoints(int hitPoints) {
        if (this.currentHitPoints + hitPoints > this.maxHitPoints) {
            this.currentHitPoints = this.maxHitPoints;
        } else if (this.currentHitPoints + hitPoints < 0) {
            this.currentHitPoints = 0;
        } else {
            this.currentHitPoints += hitPoints;
        }
    }

    public int takeDamage(int damage) {
        modifyHitPoints(-damage);
        return this.currentHitPoints;
    }

    public int heal(int healing) {
        modifyHitPoints(healing);
        return this.currentHitPoints;
    }

    public boolean isAlive() {
        return this.currentHitPoints > 0;
    }

    public int armorClass() {
        return armorClass;
    }

    public int currentHitPoints() {
        return currentHitPoints;
    }

    public int maxHitPoints() {
        return maxHitPoints;
    }

    public int initiativeModifier() {
        return initiativeModifier;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Hit points: (" + maxHitPoints + "/" + currentHitPoints + "), Armor class: " + armorClass + ", Initiative modifier: " + getSignedModifierString(initiativeModifier)
                + "\n[STR: " + abilityScore(STRENGTH).score() + " (" + getSignedModifierString(abilityScore(STRENGTH).modifier())
                + ") | DEX: " + abilityScore(DEXTERITY).score() + " (" + getSignedModifierString(abilityScore(DEXTERITY).modifier())
                + ") | CON: " + abilityScore(CONSTITUTION).score() + " (" + getSignedModifierString(abilityScore(CONSTITUTION).modifier())
                + ") | INT: " + abilityScore(INTELLIGENCE).score() + " (" + getSignedModifierString(abilityScore(INTELLIGENCE).modifier())
                + ") | WIS: " + abilityScore(WISDOM).score() + " (" + getSignedModifierString(abilityScore(WISDOM).modifier())
                + ") | CHA: " + abilityScore(CHARISMA).score() + " (" + getSignedModifierString(abilityScore(CHARISMA).modifier()) + ")]";
    }

    private String getSignedModifierString(int modifier) {
        if (modifier >= 0) {
            return "+" + modifier;
        } else {
            return Integer.toString(modifier);
        }
    }
}
