package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Encounter;

public class GetStatsForEncounterCommand extends Command {
    private final Encounter encounter;

    public GetStatsForEncounterCommand(Encounter encounter) {
        this.encounter = encounter;
    }

    public static String help() {
        return """
                Get stats for an encounter.
                Usage: getE
                You then have to select the stat you want to get.""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        String[] options = new String[]{"Get Creatures", "Get Player Characters", "Get All Involved"};
        String selection = input.requestSelection("What do you want to get?", options);

        switch (selection) {
            case "Get Creatures" -> output.displayMessage(encounter.getCreatures().toString());
            case "Get Player Characters" -> output.displayMessage(encounter.getPlayerCharacters().toString());
            case "Get All Involved" -> output.displayMessage(encounter.getInitiativeOrder().toString());
        }
    }
}
