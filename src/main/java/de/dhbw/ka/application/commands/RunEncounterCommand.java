package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.Dice;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Encounter;

public class RunEncounterCommand extends Command {

    private final Encounter encounter;
    private final Dice dice;

    public RunEncounterCommand(Encounter encounter, Dice dice) {
        this.encounter = encounter;
        this.dice = dice;
    }

    @Override
    public void execute(InputService input, OutputService output) {
        encounter.getPlayerCharacters().forEach(character ->
                encounter.addToInitiative(character.getName(), dice.rollD20() + character.getInitiativeBonus()));
        encounter.getCreatures().forEach(creature ->
                encounter.addToInitiative(creature.name(), dice.rollD20() + creature.initiativeModifier()));
        output.displayMessage(encounter.getInitiativeOrder().toString());
    }
}
