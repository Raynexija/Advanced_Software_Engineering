package de.dhbw.ka;

import de.dhbw.ka.characterClasses.CharacterClass;
import de.dhbw.ka.exception.AbilityScoreLimitException;
import de.dhbw.ka.races.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.dhbw.ka.AbilityScores.*;

public class Character {
    private String name;
    private Race race;
    private CharacterClass characterClass;

    private int level;
    private int armorClass;
    private int initiativeBonus;
    private int speed;
    private int hitPoints;

    private int passivePerception;
    private int passiveInvestigation;
    private int passiveInsight;

    private List<AbilityScores> abilityProficiencies = new ArrayList<>();
    private List<Skills> skillProficiencies = new ArrayList<>();

    private List<String> languages = new ArrayList<>();
    private List<String> equipment = new ArrayList<>();
    private HashMap<AbilityScores, AbilityScore> abilityScores = new HashMap<>();

    public Character(Race race, CharacterClass characterClass, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        setRace(race);
        setClass(characterClass);
        this.characterClass = characterClass;
        this.languages.add("Common");
        setInitialAbilityScores(strength, dexterity, constitution, intelligence, wisdom, charisma);
        calculatePassiveSenses();
        calculateArmorClass(10);
    }

    private void setInitialAbilityScores(int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma) {
        this.abilityScores.put(STRENGTH, new AbilityScore(baseStrength, this.race.getRacialBonus(STRENGTH), 0));
        this.abilityScores.put(DEXTERITY, new AbilityScore(baseDexterity, this.race.getRacialBonus(DEXTERITY), 0));
        this.abilityScores.put(CONSTITUTION, new AbilityScore(baseConstitution, this.race.getRacialBonus(CONSTITUTION), 0));
        this.abilityScores.put(INTELLIGENCE, new AbilityScore(baseIntelligence, this.race.getRacialBonus(INTELLIGENCE), 0));
        this.abilityScores.put(WISDOM, new AbilityScore(baseWisdom, this.race.getRacialBonus(WISDOM), 0));
        this.abilityScores.put(CHARISMA, new AbilityScore(baseCharisma, this.race.getRacialBonus(CHARISMA), 0));
    }

    public void calculateArmorClass(int baseAmor) {
        this.armorClass = baseAmor + this.abilityScores.get(DEXTERITY).getModifier();
    }

    public void calculatePassiveSenses() {
        if (this.skillProficiencies.contains(Skills.Perception))
            this.passivePerception = 10 + this.abilityScores.get(WISDOM).getModifier() + this.characterClass.getProficiencyBonus(this.level);
        else
            this.passivePerception = 10 + this.abilityScores.get(WISDOM).getModifier();

        if (this.skillProficiencies.contains(Skills.Investigation))
            this.passiveInvestigation = 10 + this.abilityScores.get(INTELLIGENCE).getModifier() + this.characterClass.getProficiencyBonus(this.level);
        else
            this.passiveInvestigation = 10 + this.abilityScores.get(INTELLIGENCE).getModifier();

        if (this.skillProficiencies.contains(Skills.Insight))
            this.passiveInsight = 10 + this.abilityScores.get(WISDOM).getModifier() + this.characterClass.getProficiencyBonus(this.level);
        else
            this.passiveInsight = 10 + this.abilityScores.get(WISDOM).getModifier();
    }

    private void setRace(Race race) {
        this.race = race;
        this.speed = race.getWalkingSpeed();
    }

    private void setClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
        this.abilityProficiencies.addAll(characterClass.getAbilityProficiencies());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEquipment(String item) {
        this.equipment.add(item);
        this.equipment.sort(String::compareTo);
    }

    public void removeEquipment(String item) {
        this.equipment.remove(item);
    }

    public List<String> getEquipment() {
        return this.equipment;
    }

    public void addLanguage(String language) {
        this.languages.add(language);
        this.languages.sort(String::compareTo);
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

    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    public void setInitiativeBonus(int initiative) {
        this.initiativeBonus = initiative;
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

    public String getName() {
        return this.name;
    }

    public Race getRace() {
        return race;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
