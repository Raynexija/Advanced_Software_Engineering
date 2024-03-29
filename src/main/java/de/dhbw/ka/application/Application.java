package de.dhbw.ka.application;

import de.dhbw.ka.adapter.textBased.TextBasedInputAdapter;
import de.dhbw.ka.adapter.textBased.TextBasedOutputAdapter;
import de.dhbw.ka.adapter.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.application.commands.*;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.application.interfaces.RandomNumberService;
import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.campaign.quests.Quest;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import de.dhbw.ka.plugins.inputOutput.ConsoleOutputInputSystem;
import de.dhbw.ka.plugins.rng.RandomNumberGeneratorImplementation;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private final InputService input = new TextBasedInputAdapter(new ConsoleOutputInputSystem());
    private final OutputService output = new TextBasedOutputAdapter(new ConsoleOutputInputSystem());
    private final RandomNumberService randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());

    private final Dice dice = new Dice(randomNumberGenerator);
    private final CommandController controller = new CommandController(input, output);

    private final List<Character> characters = new ArrayList<>();
    private Character currentCharacter;
    private boolean characterSelected = false;

    private final List<Encounter> encounters = new ArrayList<>();
    private Encounter currentEncounter;
    private boolean encounterSelected = false;

    private final List<Quest> quests = new ArrayList<>();
    private Quest currentQuest;
    private boolean questSelected = false;
//    private final Character testChar = CreateCharacter.named("TestChar")
//            .ofRace(new Human())
//            .isA(new Fighter())
//            .withStrength(15)
//            .withDexterity(14)
//            .withConstitution(13)
//            .withIntelligence(12)
//            .withWisdom(10)
//            .withCharisma(8)
//            .maxHitPoints(10)
//            .currentLevel(1)
//            .speaking(new String[]{"Celestial", "Elvish"})
//            .equippedWith(new String[]{"Leather Armor", "Longsword", "Dagger"})
//            .build();
//    private final Encounter testEncounter = new Encounter("Test");

    public void main() {
//        characters.add(testChar);
//        testEncounter.addPlayerCharacter(testChar);
//        testEncounter.addCreature(
//                new Creature("TestCreature", 15, 25, 0, 15, 11, 10, 1, 3, 2));
//        encounters.add(testEncounter);

        //noinspection InfiniteLoopStatement
        while (true) {
            String command = input.requestString("Enter a command: ").toLowerCase();

            switch (command.split(" ")[0]) {
                case "exit" -> exit();
                case "roll" -> {
                    if (characterSelected) rollWithCharacterSelected(command);
                    else rollWithoutSelection(command);
                }
                case "create_character", "cc" -> createCharacter();
                case "create_encounter", "ce" -> createEncounter();
                case "create_quest", "cq" -> createQuest();

                case "list_characters", "lc" -> listCharacters();
                case "list_encounters", "le" -> listEncounters();
                case "list_quests", "lq" -> listQuests();

                case "select" -> select();
                case "select_character", "sc" -> selectCharacter(command);
                case "select_encounter", "se" -> selectEncounter(command);
                case "select_quest", "sq" -> selectQuest(command);

                case "selected", "current", "curr" -> selected();
                case "selected_character", "selected_char" -> selectedCharacter();
                case "selected_encounter", "selected_enc" -> selectedEncounter();
                case "selected_quest", "selected_que" -> selectedQuest();

                case "deselect" -> deselect();
                case "deselect_character" -> deselectCharacter();
                case "deselect_encounter" -> deselectEncounter();
                case "deselect_quest" -> deselectQuest();


                case "modify_character", "modify_char" -> modifyCharacter(command);
                case "modify_encounter", "modify_enc" -> modifyEncounter(command);
                case "modify_quest", "modify_que" -> modifyQuest(command);

                case "get_Stat_Character", "getC" -> singleStatCommandCharacter(command);
                case "get_Stat_Encounter", "getE" -> singleStatCommandEncounter(command);
                case "get_Stat_Quest", "getQ" -> singleStatCommandQuest(command);

                case "start_encounter", "start" -> startEncounter();
                case "delete_character" -> deleteCharacter();
                case "finish_quest", "finishQ" -> finishQuest();
                default -> output.displayError("Unknown command");
            }
            controller.executeCommands();
        }
    }

    private void finishQuest() {
        if (checkQuestSelected()) {
            quests.add(currentQuest.markCompleted());
            output.displayMessage("Quest: \"" + currentQuest.name() + "\" finished and deselected");
            output.displayMessage("Rewards for the quest:" + currentQuest.rewards().toString());
            quests.remove(currentQuest);
        }
    }

    private void singleStatCommandQuest(String command) {
        if (command.contains("-help")) {
            output.displayMessage(GetStatsForQuestCommand.help());
        } else if (checkQuestSelected()) {
            controller.enqueueCommand(new GetStatsForQuestCommand(currentQuest));
        }
    }

    private void modifyQuest(String command) {
        if (command.contains("-help")) {
            output.displayMessage(ModifyQuestCommand.help());
        } else if (checkQuestSelected()) {
            controller.enqueueCommand(new ModifyQuestCommand(currentQuest, encounters, characters));
        }
    }

    private void listQuests() {
        if (quests.isEmpty()) {
            output.displayMessage("No quests created yet");
            return;
        }
        output.displayMessage("Quests:");
        for (Quest quest : quests) {
            output.displayMessage(quest.toString());
        }
    }

    private void createQuest() {
        controller.enqueueCommand(new CreateQuestCommand(quests, encounters, characters));
    }

    private void singleStatCommandEncounter(String command) {
        if (command.contains("-help")) {
            output.displayMessage(GetStatsForEncounterCommand.help());
        } else if (checkEncounterSelected()) {
            controller.enqueueCommand(new GetStatsForEncounterCommand(currentEncounter));
        }
    }

    private void modifyEncounter(String command) {
        if (command.contains("-help")) {
            output.displayMessage(ModifyEncounterCommand.help());
        } else if (checkEncounterSelected()) {
            controller.enqueueCommand(new ModifyEncounterCommand(currentEncounter, characters));
        }
    }

    private void startEncounter() {
        if (checkEncounterSelected()) {
            controller.enqueueCommand(new RunEncounterCommand(currentEncounter, dice));
        }
    }

    private void selected() {
        selectedCharacter();
        selectedEncounter();
    }

    private void listEncounters() {
        if (encounters.isEmpty()) {
            output.displayMessage("No encounters created yet");
            return;
        }
        output.displayMessage("Encounters:");
        for (Encounter encounter : encounters) {
            output.displayMessage(encounter.getName());
        }
    }

    private void select() {
        String toSelect = input.requestSelection("What do you want to select?", new String[]{"character", "encounter", "quest"});
        switch (toSelect) {
            case "character" -> selectCharacterViaArray();
            case "encounter" -> selectEncounterViaArray();
            case "quest" -> selectQuestViaArray();
        }
    }

    private void selectQuestViaArray() {
        if (!quests.isEmpty()) {
            String name = input.requestSelection("Which quest do you want to select?", quests.stream().map(Quest::name).toArray(String[]::new));
            getQuestViaName(name);
            return;
        }
        output.displayError("No quests created yet");
    }

    private void getQuestViaName(String name) {
        currentQuest = quests.stream().filter(quest -> quest.name().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (currentQuest == null) {
            output.displayError("No quest with that name");
        } else {
            questSelected = true;
            output.displayMessage("Selected quest: " + currentQuest.name());
        }
    }

    private void selectQuest(String command) {
        try {
            String name = command.split(" ")[1];
            getQuestViaName(name);
        } catch (IndexOutOfBoundsException e) {
            selectQuestViaArray();
        }
    }

    private void selectedQuest() {
        if (checkQuestSelected()) {
            output.displayMessage("Selected quest: " + currentQuest.name());
        }
    }

    private void deselect() {
        if (checkCharacterSelected()) {
            deselectCharacter();
        }
        if (checkEncounterSelected()) {
            deselectEncounter();
        }
        if (checkQuestSelected()) {
            deselectQuest();
        }
    }

    private void deselectQuest() {
        currentQuest = null;
        questSelected = false;
        output.displayMessage("Deselected quest");
    }

    private void createEncounter() {
        controller.enqueueCommand(new CreateEncounterCommand(encounters, characters));
    }

    private void selectEncounterViaArray() {
        if (!encounters.isEmpty()) {
            String name = input.requestSelection("Which encounter do you want to select?", encounters.stream().map(Encounter::getName).toArray(String[]::new));
            getEncounterViaName(name);
            return;
        }
        output.displayError("No encounters created yet");
    }

    private void getEncounterViaName(String name) {
        currentEncounter = encounters.stream().filter(encounter -> encounter.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (currentEncounter == null) {
            output.displayError("Encounter: \"" + name + "\" not found");
        }
        encounterSelected = true;
        output.displayMessage("Selected encounter: " + currentEncounter.getName());
    }

    private void selectEncounter(String command) {
        try {
            String name = command.split(" ")[1];
            getEncounterViaName(name);
        } catch (ArrayIndexOutOfBoundsException e) {
            selectEncounterViaArray();
        }
    }

    private void selectedEncounter() {
        if (checkEncounterSelected()) {
            output.displayMessage(currentEncounter.toString());
        }
    }

    private void deselectEncounter() {
        if (checkEncounterSelected()) {
            currentEncounter = null;
            encounterSelected = false;
            output.displayMessage("Encounter deselected");
        }
    }

    private void singleStatCommandCharacter(String command) {
        if (checkCharacterSelected()) {
            if (command.contains("-help")) {
                output.displayMessage(GetSingleCharacterStatCommand.help());
                return;
            }
            controller.enqueueCommand(new GetSingleCharacterStatCommand(currentCharacter));
        }
    }

    private boolean checkCharacterSelected() {
        if (!characterSelected) {
            output.displayError("No character selected");
            return false;
        }
        return true;
    }

    private boolean checkEncounterSelected() {
        if (!encounterSelected) {
            output.displayError("No encounter selected");
            return false;
        }
        return true;
    }

    private boolean checkQuestSelected() {
        if (!questSelected) {
            output.displayError("No quest selected");
            return false;
        }
        return true;
    }

    private void modifyCharacter(String command) {
        if (command.contains("-help")) {
            output.displayMessage(ModifyCharacterCommand.help());
        } else if (checkCharacterSelected()) {
            controller.enqueueCommand(new ModifyCharacterCommand(currentCharacter));
        }
    }

    private void createCharacter() {
        controller.enqueueCommand(new CreateCharacterCommand(characters));
    }

    private void rollWithoutSelection(String command) {
        if (command.contains("-help")) {
            output.displayMessage(DiceRollCommand.help());
            return;
        }
        controller.enqueueCommand(new DiceRollCommand(dice, command));
    }

    private void rollWithCharacterSelected(String command) {
        if (command.contains("-help")) {
            output.displayMessage(CharacterRollCommand.help());
            return;
        }
        if (command.split(" ").length > 1 && command.split(" ")[1].matches("\\d+")) {
            rollWithoutSelection(command);
            return;
        }
        output.displayMessage("Rolling for " + currentCharacter.getName());
        controller.enqueueCommand(new CharacterRollCommand(dice, command, currentCharacter));
    }

    private void listCharacters() {
        for (Character character : characters) {
            output.displayMessage(character.toString());
        }
    }

    private void selectedCharacter() {
        if (checkCharacterSelected()) {
            output.displayMessage(currentCharacter.toString());
        }
    }

    private void selectCharacterViaArray() {
        if (!characters.isEmpty()) {
            String name = input.requestSelection("Which character do you want to select?", characters.stream().map(Character::getName).toArray(String[]::new));
            getCharacterViaName(name);
            return;
        }
        output.displayError("No characters created yet");
    }

    private void getCharacterViaName(String name) {
        currentCharacter = characters.stream().filter(character -> character.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (currentCharacter == null) {
            output.displayError("Character: \"" + name + "\" not found");
        }
        characterSelected = true;
        output.displayMessage("Selected character: " + currentCharacter.getName());
    }

    private void selectCharacter(String command) {
        try {
            String name = command.split(" ")[1];
            getCharacterViaName(name);
        } catch (ArrayIndexOutOfBoundsException e) {
            selectCharacterViaArray();
        }

    }

    private void exit() {
        controller.enqueueCommand(new ExitCommand());
    }

    private void deselectCharacter() {
        if (checkCharacterSelected()) {
            currentCharacter = null;
            characterSelected = false;
            output.displayMessage("Deselected character");
        }
    }

    private void deleteCharacter() {
        if (checkCharacterSelected()) {
            characters.remove(currentCharacter);
            characterSelected = false;
            output.displayMessage("Deleted character");
        }
    }
}