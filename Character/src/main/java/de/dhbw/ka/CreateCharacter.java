package de.dhbw.ka;

import de.dhbw.ka.characterClasses.CharacterClass;
import de.dhbw.ka.races.Race;

import java.util.ArrayList;
import java.util.List;

//class to build a character using the builder pattern
public final class CreateCharacter {

    //required parameters
    private String name;
    private Race race;
    private CharacterClass characterClass;

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    private int hitPoints;
    private int level = 0;


    //optional parameters
    private int armorClass;
    private int initiativeBonus;
    private int speed;

    private List<Skills> skillProficiencies = new ArrayList<>();

    protected List<String> languages = new ArrayList<>();
    protected List<String> equipment = new ArrayList<>();

    private CreateCharacter(String name) {
        this.name = name;
    }

    public static CreateCharacter named(String name) {
        return new CreateCharacter(name);
    }

    public CreateCharacter ofRace(Race race) {
        this.race = race;
        return this;
    }

    public CreateCharacter isA(CharacterClass characterClass) {
        this.characterClass = characterClass;
        return this;
    }

    public CreateCharacter withStrength(int strength) {
        this.strength = strength;
        return this;
    }

    public CreateCharacter withDexterity(int dexterity) {
        this.dexterity = dexterity;
        return this;
    }

    public CreateCharacter withConstitution(int constitution) {
        this.constitution = constitution;
        return this;
    }

    public CreateCharacter withIntelligence(int intelligence) {
        this.intelligence = intelligence;
        return this;
    }

    public CreateCharacter withWisdom(int wisdom) {
        this.wisdom = wisdom;
        return this;
    }

    public CreateCharacter withCharisma(int charisma) {
        this.charisma = charisma;
        return this;
    }

    public CreateCharacter maxHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public BuildableCharacter currentLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level must be greater than 0");
        }
        if (level >= 20) {
            throw new IllegalArgumentException("Level must be less than 21");
        }
        this.level = level;
        return new BuildableCharacter();
    }

    public class BuildableCharacter {

        public BuildableCharacter withArmorClass(int armorClass) {
            CreateCharacter.this.armorClass = armorClass;
            return this;
        }

        public BuildableCharacter withInitiativeBonus(int initiativeBonus) {
            CreateCharacter.this.initiativeBonus = initiativeBonus;
            return this;
        }

        public BuildableCharacter walkingSpeed(int speed) {
            CreateCharacter.this.speed = speed;
            return this;
        }

        public BuildableCharacter speaking(String[] language) {
            CreateCharacter.this.languages.addAll(List.of(language));
            return this;
        }

        public BuildableCharacter equippedWith(String[] equipment) {
            CreateCharacter.this.equipment.addAll(List.of(equipment));
            return this;
        }

        public BuildableCharacter proficientIn(Skills[] skillProficiencies) {
            CreateCharacter.this.skillProficiencies.addAll(List.of(skillProficiencies));
            return this;
        }

        public Character build() {
            return CreateCharacter.this.build();
        }

    }

    private Character build() {
        Character character = new Character(this.race, this.characterClass, this.strength, this.dexterity, this.constitution, this.intelligence, this.wisdom, this.charisma);
        character.setName(this.name);
        character.setHitPoints(this.hitPoints);

        if (this.level < 0) {
            this.level = 1;
        }
        character.setLevel(this.level);

        character.setArmorClass(this.armorClass);
        character.setInitiativeBonus(this.initiativeBonus);
        character.setSpeed(this.speed);

        for (String equipment : this.equipment) {
            character.addEquipment(equipment);
        }
        for (String language : this.languages) {
            character.addLanguage(language);
        }
        for (Skills skillProficiency : this.skillProficiencies) {
            character.addSkillProficiency(skillProficiency);
        }

        return character;
    }
}
