package de.dhbw.ka.application.commands;

import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.application.Dice;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;

import static de.dhbw.ka.domain.character.AbilityScores.*;
import static de.dhbw.ka.domain.character.Skills.*;

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

        String parameter = "";
        if (commandParts.length > 1) {
            parameter = commandParts[1];
        }
        if (parameter.equals("-a") || parameter.equals("-d")) {
            String firstRoll = statRolls(statToRoll);
            String secondRoll = statRolls(statToRoll);
            output.displayMessage("You rolled a " + firstRoll + " and a " + secondRoll + " for the " + statToRoll + " check with " + getParameterasString(parameter) + ".");
        } else {
            String roll = statRolls(statToRoll);
            output.displayMessage("You rolled a " + roll + " for the " + statToRoll + " check.");
        }
    }

    private String getParameterasString(String parameter) {
        return switch (parameter) {
            case "-a" -> "advantage";
            case "-d" -> "disadvantage";
            default -> "";
        };
    }

    private String statRolls(String statToRoll) {
        int roll = dice.rollD20();
        return switch (statToRoll) {
            case "strength" ->
                    roll + character.abilityCheckModifier(STRENGTH) + " (" + roll + " +" + character.abilityCheckModifier(STRENGTH) + ")";
            case "dexterity" ->
                    roll + character.abilityCheckModifier(DEXTERITY) + " (" + roll + " +" + character.abilityCheckModifier(DEXTERITY) + ")";
            case "constitution" ->
                    roll + character.abilityCheckModifier(CONSTITUTION) + " (" + roll + " +" + character.abilityCheckModifier(CONSTITUTION) + ")";
            case "intelligence" ->
                    roll + character.abilityCheckModifier(INTELLIGENCE) + " (" + roll + " +" + character.abilityCheckModifier(INTELLIGENCE) + ")";
            case "wisdom" ->
                    roll + character.abilityCheckModifier(WISDOM) + " (" + roll + " +" + character.abilityCheckModifier(WISDOM) + ")";
            case "charisma" ->
                    roll + character.abilityCheckModifier(CHARISMA) + " (" + roll + " +" + character.abilityCheckModifier(CHARISMA) + ")";
            case "acrobatics" ->
                    roll + character.skillCheckModifier(Acrobatics) + " (" + roll + " +" + character.skillCheckModifier(Acrobatics) + ")";
            case "animal_handling" ->
                    roll + character.skillCheckModifier(Animal_Handling) + " (" + roll + " +" + character.skillCheckModifier(Animal_Handling) + ")";
            case "arcana" ->
                    roll + character.skillCheckModifier(Arcana) + " (" + roll + " +" + character.skillCheckModifier(Arcana) + ")";
            case "athletics" ->
                    roll + character.skillCheckModifier(Athletics) + " (" + roll + " +" + character.skillCheckModifier(Athletics) + ")";
            case "deception" ->
                    roll + character.skillCheckModifier(Deception) + " (" + roll + " +" + character.skillCheckModifier(Deception) + ")";
            case "history" ->
                    roll + character.skillCheckModifier(History) + " (" + roll + " +" + character.skillCheckModifier(History) + ")";
            case "insight" ->
                    roll + character.skillCheckModifier(Insight) + " (" + roll + " +" + character.skillCheckModifier(Insight) + ")";
            case "intimidation" ->
                    roll + character.skillCheckModifier(Intimidation) + " (" + roll + " +" + character.skillCheckModifier(Intimidation) + ")";
            case "investigation" ->
                    roll + character.skillCheckModifier(Investigation) + " (" + roll + " +" + character.skillCheckModifier(Investigation) + ")";
            case "medicine" ->
                    roll + character.skillCheckModifier(Medicine) + " (" + roll + " +" + character.skillCheckModifier(Medicine) + ")";
            case "nature" ->
                    roll + character.skillCheckModifier(Nature) + " (" + roll + " +" + character.skillCheckModifier(Nature) + ")";
            case "perception" ->
                    roll + character.skillCheckModifier(Perception) + " (" + roll + " +" + character.skillCheckModifier(Perception) + ")";
            case "performance" ->
                    roll + character.skillCheckModifier(Performance) + " (" + roll + " +" + character.skillCheckModifier(Performance) + ")";
            case "persuasion" ->
                    roll + character.skillCheckModifier(Persuasion) + " (" + roll + " +" + character.skillCheckModifier(Persuasion) + ")";
            case "religion" ->
                    roll + character.skillCheckModifier(Religion) + " (" + roll + " +" + character.skillCheckModifier(Religion) + ")";
            case "sleight_of_hand" ->
                    roll + character.skillCheckModifier(Sleight_of_Hand) + " (" + roll + " +" + character.skillCheckModifier(Sleight_of_Hand) + ")";
            case "stealth" ->
                    roll + character.skillCheckModifier(Stealth) + " (" + roll + " +" + character.skillCheckModifier(Stealth) + ")";
            case "survival" ->
                    roll + character.skillCheckModifier(Survival) + " (" + roll + " +" + character.skillCheckModifier(Survival) + ")";
            case "initiative" ->
                    roll + character.getInitiativeBonus() + " (" + roll + " +" + character.getInitiativeBonus() + ")";
            default -> throw new IllegalStateException("Unexpected value: " + statToRoll);
        };
    }
}
