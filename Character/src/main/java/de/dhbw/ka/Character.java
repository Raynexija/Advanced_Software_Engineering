package de.dhbw.ka;

import de.dhbw.ka.characterClasses.CharacterClass;
import de.dhbw.ka.exception.AbilityScoreLimitException;
import de.dhbw.ka.races.Human;
import de.dhbw.ka.races.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.dhbw.ka.AbilityScores.*;

public class Character {
    protected String name;
    protected Race race;
    protected CharacterClass characterClass;

    private int level;
    private int armorClass;
    private int initiative;
    private int speed;
    private int hitPoints;

    private List<AbilityScores> abilityProficiencies = new ArrayList<>();
    private List<Skills> skillProficiencies = new ArrayList<>();

    protected List<String> languages = new ArrayList<>();
    protected List<String> equipment = new ArrayList<>();
    private HashMap<AbilityScores, AbilityScore> abilityScores = new HashMap<>();

    public Character() {
        this.languages.add("Common");
        this.race = new Human();
        setInitialAbilityScores(15, 10, 10, 10, 10, 10);
    }

    private void setInitialAbilityScores(int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma) {
        this.abilityScores.put(STRENGTH, new AbilityScore(baseStrength, this.race.getRacialBonus(STRENGTH), 0));
        this.abilityScores.put(DEXTERITY, new AbilityScore(baseDexterity, this.race.getRacialBonus(DEXTERITY), 0));
        this.abilityScores.put(CONSTITUTION, new AbilityScore(baseConstitution, this.race.getRacialBonus(CONSTITUTION), 0));
        this.abilityScores.put(INTELLIGENCE, new AbilityScore(baseIntelligence, this.race.getRacialBonus(INTELLIGENCE), 0));
        this.abilityScores.put(WISDOM, new AbilityScore(baseWisdom, this.race.getRacialBonus(WISDOM), 0));
        this.abilityScores.put(CHARISMA, new AbilityScore(baseCharisma, this.race.getRacialBonus(CHARISMA), 0));
    }

    public void addEquipment(String item) {
        this.equipment.add(item);
    }

    public List<String> getEquipment() {
        return this.equipment;
    }

    public void addLanguage(String language) {
        this.languages.add(language);
    }

    public void addSkillProficiency(Skills skill) {
        this.skillProficiencies.add(skill);
    }


    public void setFlatAbilityScore(AbilityScores abilityScore, int value) {
        this.abilityScores.put(abilityScore, new AbilityScore(value, 0, 0));
    }

    public void addAbilityBonus(AbilityScores abilityScore, int bonus) {
        AbilityScore ability = this.abilityScores.get(abilityScore);
        try {
            this.abilityScores.put(abilityScore, new AbilityScore(ability.getBaseScore(),
                    this.race.getRacialBonus(abilityScore),
                    ability.getAdditionalBonus() + bonus));
        } catch (AbilityScoreLimitException e) {
            System.out.println("Ability Score must not exceed 20");
        }
    }

    public int skillCheckModifier(Skills skill) {
        if (skillProficiencies.contains(skill)) {
            return abilityScores.get(skill.ability).modifier + this.characterClass.getProficiencyBonus(this.level);
        } else {
            return abilityScores.get(skill.ability).modifier;
        }
    }

    public int savingThrowModifier(AbilityScores abilityScore) {
        if (abilityProficiencies.contains(abilityScore)) {
            return this.abilityScores.get(abilityScore).modifier + this.characterClass.getProficiencyBonus(this.level);
        } else {
            return this.abilityScores.get(abilityScore).modifier;
        }
    }

    public int abilityCheckModifier(AbilityScores abilityScore) {
        return this.abilityScores.get(abilityScore).modifier;
    }

    public AbilityScore getAbilityScore(AbilityScores abilityScore) {
        return this.abilityScores.get(abilityScore);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
