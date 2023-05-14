package de.dhbw.ka.commands;

import de.dhbw.ka.Character;
import de.dhbw.ka.Dice;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

import static de.dhbw.ka.AbilityScores.*;
import static de.dhbw.ka.Skills.*;

public class CharacterRollCommand extends Command {

    private final Character character;
    private final Dice dice;
    private final String commandMessage;

    public CharacterRollCommand(Dice dice, String commandMessage, Character character) {
        this.dice = dice;
        this.commandMessage = commandMessage;
        this.character = character;
    }

    public static String help() {
        return """
                Rolls a check for a character.
                When a character is selected you can roll for all available stats.
                Add -a to roll with advantage or -d to roll with disadvantage.
                Usage: roll <parameter>
                You then have to select the stat you want to roll for.""";
    }

    public String[] availableStatForRolling = {"strength", "dexterity", "constitution", "intelligence", "wisdom",
            "charisma", "acrobatics", "animal_handling", "arcana", "athletics", "deception", "history", "insight",
            "intimidation", "investigation", "medicine", "nature", "perception", "performance", "persuasion", "religion",
            "sleight_of_hand", "stealth", "survival", "initiative", "speed"};

    @Override
    public void execute(InputService input, OutputService output) {
        //switch over all possible roles for the character
        String[] commandParts = commandMessage.split(" ");
        String statToRoll = input.requestSelection("What stat do you want to roll?", availableStatForRolling);
        String parameter = commandParts[1].toLowerCase().toLowerCase();
        if (parameter.equals("-a") || parameter.equals("-d")) {
            int firstRoll = statRolls(statToRoll);
            int secondRoll = statRolls(statToRoll);
            output.displayMessage("You rolled a " + firstRoll + " and a " + secondRoll + " for the " + statToRoll + " check with " + getParameterasString(parameter) + ".");
        } else {
            int roll = statRolls(statToRoll);
            output.displayMessage("You rolled a " + roll + " for the " + statToRoll + " check.");
        }
    }

    private String getParameterasString(String parameter) {
        return switch (parameter) {
            case "-a" -> "advantage";
            case "-d" -> "disadvantage";
        };
    }

    private int statRolls(String statToRoll) {
        return switch (statToRoll) {
            case "strength" -> dice.rollD20() + character.abilityCheckModifier(STRENGTH);
            case "dexterity" -> dice.rollD20() + character.abilityCheckModifier(DEXTERITY);
            case "constitution" -> dice.rollD20() + character.abilityCheckModifier(CONSTITUTION);
            case "intelligence" -> dice.rollD20() + character.abilityCheckModifier(INTELLIGENCE);
            case "wisdom" -> dice.rollD20() + character.abilityCheckModifier(WISDOM);
            case "charisma" -> dice.rollD20() + character.abilityCheckModifier(CHARISMA);
            case "acrobatics" -> dice.rollD20() + character.skillCheckModifier(Acrobatics);
            case "animal_handling" -> dice.rollD20() + character.skillCheckModifier(Animal_Handling);
            case "arcana" -> dice.rollD20() + character.skillCheckModifier(Arcana);
            case "athletics" -> dice.rollD20() + character.skillCheckModifier(Athletics);
            case "deception" -> dice.rollD20() + character.skillCheckModifier(Deception);
            case "history" -> dice.rollD20() + character.skillCheckModifier(History);
            case "insight" -> dice.rollD20() + character.skillCheckModifier(Insight);
            case "intimidation" -> dice.rollD20() + character.skillCheckModifier(Intimidation);
            case "investigation" -> dice.rollD20() + character.skillCheckModifier(Investigation);
            case "medicine" -> dice.rollD20() + character.skillCheckModifier(Medicine);
            case "nature" -> dice.rollD20() + character.skillCheckModifier(Nature);
            case "perception" -> dice.rollD20() + character.skillCheckModifier(Perception);
            case "performance" -> dice.rollD20() + character.skillCheckModifier(Performance);
            case "persuasion" -> dice.rollD20() + character.skillCheckModifier(Persuasion);
            case "religion" -> dice.rollD20() + character.skillCheckModifier(Religion);
            case "sleight_of_hand" -> dice.rollD20() + character.skillCheckModifier(Sleight_of_Hand);
            case "stealth" -> dice.rollD20() + character.skillCheckModifier(Stealth);
            case "survival" -> dice.rollD20() + character.skillCheckModifier(Survival);
            case "initiative" -> dice.rollD20() + character.getInitiativeBonus();
        };
    }
}
