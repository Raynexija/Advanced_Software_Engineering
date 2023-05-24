package de.dhbw.ka.application.commands;

import de.dhbw.ka.domain.character.AbilityScores;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.Skills;
import de.dhbw.ka.domain.character.characterClasses.*;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.character.races.*;

import java.util.Arrays;
import java.util.List;

public class CreateCharacterCommand extends Command {
    private final List<Character> characters;
    private String[] skills = new String[]{"Acrobatics", "Animal_Handling", "Arcana", "Athletics", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature", "Perception", "Performance", "Persuasion", "Religion", "Sleight_of_Hand", "Stealth", "Survival"};

    public CreateCharacterCommand(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void execute(InputService input, OutputService output) {
        CreateCharacter.BuildableCharacter character = CreateCharacter
                .named(input.requestString("Enter the name of the character: "))
                .ofRace(getRace(input, output))
                .isA(getCharacterClass(input, output))
                .withStrength(input.requestInt("Enter the strength of the character: "))
                .withDexterity(input.requestInt("Enter the dexterity of the character: "))
                .withConstitution(input.requestInt("Enter the constitution of the character: "))
                .withIntelligence(input.requestInt("Enter the intelligence of the character: "))
                .withWisdom(input.requestInt("Enter the wisdom of the character: "))
                .withCharisma(input.requestInt("Enter the charisma of the character: "))
                .maxHitPoints(input.requestInt("Enter the max hit points of the character: "))
                .currentLevel(input.requestInt("Enter the current level of the character: "));

        boolean stillBuilding = true;
        String[] optionalParameters = new String[]{"Add skill proficiency", "Add language (Common is assigned automatically)", "Add equipment", "Add initiative bonus", "Add armor class", "Finish character"};
        while (stillBuilding) {
            String choice = input.requestSelection("What do you want to do?", optionalParameters);
            switch (choice) {
                case "Add skill proficiency" ->
                        character.proficientInOne(getSkillFromString(input.requestSelection("Chose a skill:", skills)));
                case "Add language (Common is assigned automatically" ->
                        character.speakingOne(input.requestString("Enter one language: "));
                case "Add equipment" ->
                        character.equippedWithOne(input.requestString("Enter one item to the inventory: "));
                case "Add initiative bonus" -> {
                    character.withInitiativeBonus(input.requestInt("Enter the initiative bonus of the character: "));
                    optionalParameters = Arrays.stream(optionalParameters).filter(s -> !s.equals("Add initiative bonus")).toArray(String[]::new);
                }
                case "Add armor class" -> {
                    character.withArmorClass(input.requestInt("Enter the base armor class of the armor worn by the Character (without bonus): "));
                    optionalParameters = Arrays.stream(optionalParameters).filter(s -> !s.equals("Add armor class")).toArray(String[]::new);
                }
                case "Finish character" -> stillBuilding = false;
            }
        }

        Character buildedCharacter = character.build();
        characters.add(buildedCharacter);
        output.displayMessage(buildedCharacter.toString());
    }

    private CharacterClass getCharacterClass(InputService input, OutputService output) {
        String characterClassSelection = input.requestSelection("Chose a class:",
                new String[]{"Fighter", "Rogue", "Wizard", "Cleric", "Barbarian", "Bard", "Druid", "Monk", "Paladin", "Ranger", "Sorcerer", "Warlock"});

        switch (characterClassSelection) {
            case "Fighter" -> {
                output.displayMessage("You chose Fighter");
                return new Fighter();
            }
            case "Rogue" -> {
                output.displayMessage("You chose Rogue");
                return new Rogue();
            }
            case "Wizard" -> {
                output.displayMessage("You chose Wizard");
                return new Wizard();
            }
            case "Cleric" -> {
                output.displayMessage("You chose Cleric");
                return new Cleric();
            }
            case "Barbarian" -> {
                output.displayMessage("You chose Barbarian");
                return new Barbarian();
            }
            case "Bard" -> {
                output.displayMessage("You chose Bard");
                return new Bard();
            }
            case "Druid" -> {
                output.displayMessage("You chose Druid");
                return new Druid();
            }
            case "Monk" -> {
                output.displayMessage("You chose Monk");
                return new Monk();
            }
            case "Paladin" -> {
                output.displayMessage("You chose Paladin");
                return new Paladin();
            }
            case "Ranger" -> {
                output.displayMessage("You chose Ranger");
                return new Ranger();
            }
            case "Sorcerer" -> {
                output.displayMessage("You chose Sorcerer");
                return new Sorcerer();
            }
            case "Warlock" -> {
                output.displayMessage("You chose Warlock");
                return new Warlock();
            }
        }
        return null;
    }

    private Skills getSkillFromString(String skill) {
        this.skills = Arrays.stream(this.skills).filter(s -> !s.equals(skill)).toArray(String[]::new);
        return Skills.valueOf(skill);
    }

    private Race getRace(InputService input, OutputService output) {
        String race = input.requestSelection("Chose a race:", new String[]{"Human", "Elf", "Dwarf", "Halfling", "Gnome", "Half-Elf", "Half-Orc", "Tiefling"});

        switch (race) {
            case "Human" -> {
                output.displayMessage("You chose Human as your race");
                return new Human();
            }
            case "Elf" -> {
                output.displayMessage("You chose Elf as your race");
                return new Elf();
            }
            case "Dwarf" -> {
                output.displayMessage("You chose Dwarf as your race");
                return new Dwarf();
            }
            case "Halfling" -> {
                output.displayMessage("You chose Halfling as your race");
                return new Halfling();
            }
            case "Gnome" -> {
                output.displayMessage("You chose Gnome as your race");
                return new Gnome();
            }
            case "Half-Elf" -> {
                output.displayMessage("You chose Half-Elf as your race");
                return new HalfElf(getAbilitiesToIncrease("Half-Elf", input, output));
            }
            case "Half-Orc" -> {
                output.displayMessage("You chose Half-Orc as your race");
                return new HalfOrc();
            }
            case "Tiefling" -> {
                output.displayMessage("You chose Tiefling as your race");
                return new Tiefling();
            }
            default -> {
                return null;
            }
        }
    }

    private AbilityScores[] getAbilitiesToIncrease(String chosenRace, InputService input, OutputService output) {
        String[] abilityToChose = new String[]{"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
        AbilityScores[] abilitiesToIncrease = new AbilityScores[2];

        switch (chosenRace) {
            case "Half-Elf" -> {
                abilityToChose = Arrays.stream(abilityToChose).filter(s -> !s.equalsIgnoreCase(HalfElf.getAlreadyIncreasedAbility().toString())).toArray(String[]::new);
                for (int i = 0; i < 2; i++) {
                    String ability = input.requestSelection("Chose an ability to increase by one:", abilityToChose);
                    abilityToChose = Arrays.stream(abilityToChose).filter(s -> !s.equalsIgnoreCase(ability)).toArray(String[]::new);
                    abilitiesToIncrease[i] = AbilityScores.valueOf(ability.toUpperCase());
                }
            }
            case "TBD" -> {
                //Here to provided easy extendability If more races are added, which get to Choose their ability scores
            }
        }
        return abilitiesToIncrease;
    }
}
