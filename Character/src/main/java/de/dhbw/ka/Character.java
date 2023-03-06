package de.dhbw.ka;

import de.dhbw.ka.characterClasses.CharacterClass;
import de.dhbw.ka.races.Race;

public class Character {
    protected String name;
    protected Race race;
    protected CharacterClass characterClass;

    protected AbilityScore strength;
    protected AbilityScore dexterity;
    protected AbilityScore constitution;
    protected AbilityScore intelligence;
    protected AbilityScore wisdom;
    protected AbilityScore charisma;

    public Character() {
        setAbilityScores(10, 10, 10, 10, 10, 10);
    }

    private void setAbilityScores(int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma) {
        this.strength = new AbilityScore(baseStrength, this.race.getRacialBonus(AbilityScores.STRENGTH));
        this.dexterity = new AbilityScore(baseDexterity, this.race.getRacialBonus(AbilityScores.DEXTERITY));
        this.constitution = new AbilityScore(baseConstitution, this.race.getRacialBonus(AbilityScores.CONSTITUTION));
        this.intelligence = new AbilityScore(baseIntelligence, this.race.getRacialBonus(AbilityScores.INTELLIGENCE));
        this.wisdom = new AbilityScore(baseWisdom, this.race.getRacialBonus(AbilityScores.WISDOM));
        this.charisma = new AbilityScore(baseCharisma, this.race.getRacialBonus(AbilityScores.CHARISMA));
    }

    public int savingSavingThrowModifier(AbilityScores abilityScore) {
        switch (abilityScore) {
            case STRENGTH:
                if (this.characterClass.isProficient(abilityScore.STRENGTH))
                    return this.strength.modifier + 2;
                else
                    return this.strength.modifier;
            case DEXTERITY:
                if (this.characterClass.isProficient(abilityScore.DEXTERITY))
                    return this.dexterity.modifier + 2;
                else
                    return this.dexterity.modifier;
            case CONSTITUTION:
                if (this.characterClass.isProficient(abilityScore.CONSTITUTION))
                    return this.constitution.modifier + 2;
                else
                    return this.constitution.modifier;
            case INTELLIGENCE:
                if (this.characterClass.isProficient(abilityScore.INTELLIGENCE))
                    return this.intelligence.modifier + 2;
                else
                    return this.intelligence.modifier;
            case WISDOM:
                if (this.characterClass.isProficient(abilityScore.WISDOM))
                    return this.wisdom.modifier + 2;
                else
                    return this.wisdom.modifier;
            case CHARISMA:
                if (this.characterClass.isProficient(abilityScore.CHARISMA))
                    return this.charisma.modifier + 2;
                else
                    return this.charisma.modifier;
        }
        throw new IllegalArgumentException("Illegal Ability Score");
    }

    @Override
    public String toString() {
        return "Character{" +
                "Strength=" + strength +
                ", Dexterity=" + dexterity +
                ", Constitution=" + constitution +
                ", Intelligence=" + intelligence +
                ", Wisdom=" + wisdom +
                ", Charisma=" + charisma +
                '}';
    }
}
