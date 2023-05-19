package de.dhbw.ka.domain.character;

import de.dhbw.ka.domain.character.characterClasses.CharacterClass;
import de.dhbw.ka.domain.character.exception.AbilityScoreLimitException;
import de.dhbw.ka.domain.character.races.Race;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.dhbw.ka.domain.character.AbilityScores.*;

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

    private final List<AbilityScores> abilityProficiencies = new ArrayList<>();
    private final List<Skills> skillProficiencies = new ArrayList<>();

    private final List<String> languages = new ArrayList<>();
    private final List<String> equipment = new ArrayList<>();
    private final HashMap<AbilityScores, AbilityScore> abilityScores = new HashMap<>();

    public Character(Race race, CharacterClass characterClass, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        setRace(race);
        setClass(characterClass);
        this.characterClass = characterClass;
        this.languages.add("Common");
        setInitialAbilityScores(strength, dexterity, constitution, intelligence, wisdom, charisma);
        calculatePassiveSenses();
        setArmorClass(10);
    }

    /**
     * Method to set the initial ability scores of the character, including racial bonuses
     *
     * @param baseStrength     the base strength of the character without any bonuses
     * @param baseDexterity    the base dexterity of the character without any bonuses
     * @param baseConstitution the base constitution of the character without any bonuses
     * @param baseIntelligence the base intelligence of the character without any bonuses
     * @param baseWisdom       the base wisdom of the character without any bonuses
     * @param baseCharisma     the base charisma of the character without any bonuses
     */
    private void setInitialAbilityScores(int baseStrength, int baseDexterity, int baseConstitution, int baseIntelligence, int baseWisdom, int baseCharisma) {
        this.abilityScores.put(STRENGTH, new AbilityScore(baseStrength, this.race.getRacialBonus(STRENGTH), 0));
        this.abilityScores.put(DEXTERITY, new AbilityScore(baseDexterity, this.race.getRacialBonus(DEXTERITY), 0));
        this.abilityScores.put(CONSTITUTION, new AbilityScore(baseConstitution, this.race.getRacialBonus(CONSTITUTION), 0));
        this.abilityScores.put(INTELLIGENCE, new AbilityScore(baseIntelligence, this.race.getRacialBonus(INTELLIGENCE), 0));
        this.abilityScores.put(WISDOM, new AbilityScore(baseWisdom, this.race.getRacialBonus(WISDOM), 0));
        this.abilityScores.put(CHARISMA, new AbilityScore(baseCharisma, this.race.getRacialBonus(CHARISMA), 0));
    }

    /**
     * Calculates and sets the armor class of the character, based on the base armor value and the dexterity modifier
     *
     * @param baseAmor the armor value given by the armor worn by the character
     * @return the calculated armor class
     */
    public int setArmorClass(int baseAmor) {
        this.armorClass = baseAmor + this.abilityScores.get(DEXTERITY).getModifier();
        return this.armorClass;
    }

    /**
     * Set a fixed value for the armor class
     *
     * @param armorClass the flat value without any modifiers
     */
    public void setFlatArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    /**
     * Calculates the passive senses of the character
     * (passive perception, passive investigation, passive insight)
     */
    private void calculatePassiveSenses() {
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

    /**
     * Adds an item to the equipment list and sorts it alphabetically
     *
     * @param item the item to be added to the character's equipment
     */
    public void addEquipment(String item) {
        this.equipment.add(item);
        this.equipment.sort(String::compareTo);
    }

    /**
     * Removes a given item from the equipment list
     *
     * @param item the item to be removed from the character's equipment
     */
    public void removeEquipment(String item) {
        this.equipment.remove(item);
    }

    /**
     * Returns the alphabetically sorted list of equipment the character has
     *
     * @return the list of equipment
     */
    public List<String> getEquipment() {
        return this.equipment;
    }

    /**
     * Adds a language the character can speak and read
     *
     * @param language the language to be added to the character's language list
     */
    public void addLanguage(String language) {
        this.languages.add(language);
        this.languages.sort(String::compareTo);
    }

    /**
     * Adds a skill the character is proficient in.
     * <p>
     * If necessary, it then recalculates the passive senses of the character to ensure they are up-to-date.
     *
     * @param skill the skill which the character got proficient in
     */
    public void addSkillProficiency(Skills skill) {
        this.skillProficiencies.add(skill);
        if (skill == Skills.Perception || skill == Skills.Investigation || skill == Skills.Insight)
            calculatePassiveSenses();
    }


    /**
     * Sets the ability score of the character to a fixed value,
     * this can happen through an item or spell effect.
     * <p>
     * If necessary, it then recalculates the passive senses of the character to ensure they are up-to-date.
     *
     * @param abilityScore the ability score to be set
     * @param value        the value the ability score should be set to
     */
    public void setFlatAbilityScore(AbilityScores abilityScore, int value) {
        this.abilityScores.put(abilityScore, new AbilityScore(value, 0, 0));
        if (abilityScore == WISDOM || abilityScore == INTELLIGENCE)
            calculatePassiveSenses();
    }

    /**
     * Adds a bonus to the ability score of the character and returns the new value
     * it may not exceed 20.
     * <p>
     * If necessary, it then recalculates the passive senses of the character to ensure they are up-to-date.
     *
     * @param abilityScore the ability score to be modified
     * @param bonus        the bonus to be added to the ability score
     * @return the new value of the ability score including the bonus
     */
    public int addAbilityBonus(AbilityScores abilityScore, int bonus) {
        AbilityScore ability = this.abilityScores.get(abilityScore);
        try {
            this.abilityScores.put(abilityScore, new AbilityScore(ability.getBaseScore(),
                    this.race.getRacialBonus(abilityScore),
                    ability.getAdditionalBonus() + bonus));
        } catch (AbilityScoreLimitException e) {
            System.out.println("Ability Score must not exceed 20");
        }
        if (abilityScore == WISDOM || abilityScore == INTELLIGENCE)
            calculatePassiveSenses();
        return this.abilityScores.get(abilityScore).getScore();
    }

    /**
     * Returns the modifier the character has for a given skill check
     *
     * @param skill the skill to be checked
     * @return the modifier for the skill check
     */
    public int skillCheckModifier(Skills skill) {
        if (skillProficiencies.contains(skill)) {
            return abilityScores.get(skill.ability).getModifier() + this.characterClass.getProficiencyBonus(this.level);
        } else {
            return abilityScores.get(skill.ability).getModifier();
        }
    }

    /**
     * Returns the modifier the character has for a given saving throw
     *
     * @param abilityScore the ability score to be checked
     * @return the modifier for the saving throw
     */
    public int savingThrowModifier(AbilityScores abilityScore) {
        if (abilityProficiencies.contains(abilityScore)) {
            return this.abilityScores.get(abilityScore).getModifier() + this.characterClass.getProficiencyBonus(this.level);
        } else {
            return this.abilityScores.get(abilityScore).getModifier();
        }
    }

    /**
     * Returns the modifier the character has for a given ability check
     *
     * @param abilityScore the ability score to be checked
     * @return the modifier for the ability check
     */
    public int abilityCheckModifier(AbilityScores abilityScore) {
        return this.abilityScores.get(abilityScore).getModifier();
    }

    /**
     * Returns a given ability score of the character
     *
     * @param abilityScore the ability score to be returned
     * @return the ability score
     */
    public AbilityScore getAbilityScore(AbilityScores abilityScore) {
        return this.abilityScores.get(abilityScore);
    }

    /**
     * Returns the current level of the character
     *
     * @return the level of the character
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the character
     *
     * @param level the level to be set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the current Armor Class of the character
     *
     * @return the armor class of the character
     */
    public int getArmorClass() {
        return armorClass;
    }

    /**
     * Returns the initiative bonus of the character
     *
     * @return the initiative bonus
     */
    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    /**
     * Sets the initiative bonus of the character
     *
     * @param initiative the initiative bonus to be set
     */
    public void setInitiativeBonus(int initiative) {
        this.initiativeBonus = initiative;
    }

    /**
     * Returns the current speed of the character
     *
     * @return the speed of the character
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the character
     *
     * @param speed the speed to be set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Returns the current hit points of the character
     *
     * @return the hit points of the character
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Sets the hit points of the character
     *
     * @param hitPoints the hit points to be set
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Returns the name of the character
     *
     * @return the name of the character
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the Race of the character
     *
     * @return the Character's race
     */
    public Race getRace() {
        return race;
    }

    /**
     * Returns the Class of the character
     *
     * @return the Character's class
     */
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    /**
     * Returns a list of all the languages the character can speak
     *
     * @return a list of languages the character can speak
     */
    public List<String> getLanguages() {
        return languages;
    }

    public String[] getUnproficientSkills() {
        List<String> unproficientSkills = new ArrayList<>();
        for (Skills skill : Skills.values()) {
            if (!this.skillProficiencies.contains(skill)) {
                unproficientSkills.add(skill.toString());
            }
        }
        return unproficientSkills.toArray(new String[0]);
    }

    /**
     * Subtracts damage taken from the character's hit points
     *
     * @param damage the damage sustained
     * @return the remaining hit points
     */
    public int takeDamage(int damage) {
        this.hitPoints -= damage;
        return this.hitPoints;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
                "Race: " + this.race.getClass().toString() + "\n" +
                "Class: " + this.characterClass.getClass().toString() + "\n" +
                "Level: " + this.level + "\n" +
                "Armor Class: " + this.armorClass + "\n" +
                "Initiative Bonus: " + this.initiativeBonus + "\n" +
                "Speed: " + this.speed + "\n" +
                "Hit Points: " + this.hitPoints + "\n" +
                "Passive Perception: " + this.passivePerception + "\n" +
                "Passive Investigation: " + this.passiveInvestigation + "\n" +
                "Passive Insight: " + this.passiveInsight + "\n" +
                "Ability Scores: " + "\n" +
                "Strength: " + this.abilityScores.get(STRENGTH).getScore() + " (" + this.abilityScores.get(STRENGTH).getModifier() + ")" + "\n" +
                "Dexterity: " + this.abilityScores.get(DEXTERITY).getScore() + " (" + this.abilityScores.get(DEXTERITY).getModifier() + ")" + "\n" +
                "Constitution: " + this.abilityScores.get(CONSTITUTION).getScore() + " (" + this.abilityScores.get(CONSTITUTION).getModifier() + ")" + "\n" +
                "Intelligence: " + this.abilityScores.get(INTELLIGENCE).getScore() + " (" + this.abilityScores.get(INTELLIGENCE).getModifier() + ")" + "\n" +
                "Wisdom: " + this.abilityScores.get(WISDOM).getScore() + " (" + this.abilityScores.get(WISDOM).getModifier() + ")" + "\n" +
                "Charisma: " + this.abilityScores.get(CHARISMA).getScore() + " (" + this.abilityScores.get(CHARISMA).getModifier() + ")" + "\n" +
                "Ability Proficiencies: " + this.abilityProficiencies + "\n" +
                "Skill Proficiencies: " + this.skillProficiencies + "\n" +
                "Languages: " + this.languages + "\n" +
                "Equipment: " + this.equipment + "\n";
    }


}
