package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.character.Character;

public class GetSingleCharacterStatCommand extends Command {
    private final String commandMessage;
    private final Character character;

    public static String[] availableOptions = {
            "Get equipment", "Get languages", "Get skill proficiencies", "Get speed",
            "Get armor class", "Get initiative bonus", "Get hit points", "Get level",
            "Get ability scores"};

    public GetSingleCharacterStatCommand(String commandMessage, Character character) {
        this.commandMessage = commandMessage;
        this.character = character;
    }

    public static String help() {
        return """
                Gets a single stat of a character.
                When a character is selected you can get all available stats.
                Usage: getC
                You then have to select the stat you want to get.""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        String statToKnow = input.requestSelection("What do you want to know about the" + character.getName() + "?", availableOptions);

        switch (statToKnow) {
            case "Get equipment" -> output.displayMessage("Your equipment is: " + character.getEquipment().toString());
            case "Get languages" -> output.displayMessage("Your languages are: " + character.getLanguages().toString());
            case "Get skill proficiencies" ->
                    output.displayMessage("Your skill proficiencies are: " + character.getAllSkillProficiencies().toString());
            case "Get speed" -> output.displayMessage("Your speed is: " + character.getSpeed());
            case "Get armor class" -> output.displayMessage("Your armor class is: " + character.getArmorClass());
            case "Get initiative bonus" ->
                    output.displayMessage("Your initiative bonus is: " + character.getInitiativeBonus());
            case "Get hit points" ->
                    output.displayMessage("Your hit points are: (" + character.getHitPoints() + "/" + character.getMaxHitPoints() + ")");
            case "Get level" -> output.displayMessage("Your level is: " + character.getLevel());
            case "Get ability scores" ->
                    output.displayMessage("Your ability scores are: " + character.getAllAbilityScores().toString());
        }
    }
}
