package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.quests.Quest;

public class GetStatsForQuestCommand extends Command {
    private final Quest quest;

    public GetStatsForQuestCommand(Quest currentQuest) {
        this.quest = currentQuest;
    }

    public static String help() {
        return """
                Displays the stats of the current quest.
                Usage: getQ
                You then have to select the stat you want to get.""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        String[] options = new String[]{"Get name", "Get description", "Get reward", "Get involved characters", "Back"};
        String selection = input.requestSelection("What do you want to get?", options);

        switch (selection) {
            case "Get name" -> output.displayMessage(quest.name());
            case "Get description" -> output.displayMessage(quest.description());
            case "Get reward" -> output.displayMessage(quest.rewards().toString());
            case "Get involved characters" -> output.displayMessage(quest.assignedCharacters().toString());
            case "Back" -> output.displayMessage("Going back.");
        }
    }
}
