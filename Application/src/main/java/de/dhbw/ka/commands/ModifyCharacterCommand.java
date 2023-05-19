package de.dhbw.ka.commands;

import de.dhbw.ka.AbilityScores;
import de.dhbw.ka.Character;
import de.dhbw.ka.Skills;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

public class ModifyCharacterCommand extends Command {

    private final String commandMessage;
    private final Character character;

    public static String[] availableOptions = {
            "Add equipment", "Remove equipment", "Add language", "addSkillProficiency", "Set speed",
            "Set armor class", "Set flat armor class", "Set initiative bonus", "Set hit points", "Set level",
            "Add ability bonus"};

    public ModifyCharacterCommand(String commandMessage, Character character) {
        this.commandMessage = commandMessage;
        this.character = character;
    }

    public static String help() {
        return """
                Modifies a character.
                When a character is selected you can modify all available stats.
                Usage: modify 
                You then have to select the stat you want to modify.""";
    }


    @Override
    public void execute(InputService input, OutputService output) {
        String toModify = input.requestSelection("What do you want to modify?", availableOptions);

        switch (toModify) {
            case "Add equipment" -> {
                String equipment = input.requestString("What equipment do you want to add?");
                character.addEquipment(equipment);
                output.displayMessage("You added " + equipment + " to your equipment.");
            }
            case "Remove equipment" -> {
                String equipment = input.requestString("What equipment do you want to remove?");
                character.removeEquipment(equipment);
                output.displayMessage("You removed " + equipment + " from your equipment.");
            }
            case "Add language" -> {
                String language = input.requestString("What language do you want to add?");
                character.addLanguage(language);
                output.displayMessage("You added " + language + " to your languages.");
            }
            case "addSkillProficiency" -> {
                String[] unproficientSkills = character.getUnproficientSkills();
                String skill = input.requestSelection("What skill do you want to add?", unproficientSkills);
                character.addSkillProficiency(Skills.valueOf(skill));
                output.displayMessage("You added " + skill + " to your skill proficiencies.");
            }
            case "Set speed" -> {
                int speed = input.requestInt("What speed do you want to set?");
                character.setSpeed(speed);
                output.displayMessage("You set your speed to " + speed + ".");
            }
            case "Set armor class" -> {
                int armorClass = input.requestInt("What armor class has the armor that the character wears?");
                character.setArmorClass(armorClass);
                output.displayMessage("You set your armor class to " + armorClass + ".");
            }
            case "Set flat armor class" -> {
                int flatArmorClass = input.requestInt("What flat armor class do you want to set?");
                character.setFlatArmorClass(flatArmorClass);
                output.displayMessage("You set your flat armor class to " + flatArmorClass + ".");
            }
            case "Set initiative bonus" -> {
                int initiativeBonus = input.requestInt("What initiative bonus do you want to set?");
                character.setInitiativeBonus(initiativeBonus);
                output.displayMessage("You set your initiative bonus to " + initiativeBonus + ".");
            }
            case "Set hit points" -> {
                int hitPoints = input.requestInt("What hit points do you want to set?");
                character.setHitPoints(hitPoints);
                output.displayMessage("You set your hit points to " + hitPoints + ".");
            }
            case "Set level" -> {
                int level = input.requestInt("What level do you want to set?");
                character.setLevel(level);
                output.displayMessage("You set your level to " + level + ".");
            }
            case "Add ability bonus" -> {
                String[] abilities = new String[] {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};
                String ability = input.requestSelection("What ability do you want to add?", abilities);
                int bonus = input.requestInt("What bonus do you want to add?");
                character.addAbilityBonus(AbilityScores.valueOf(ability.toUpperCase()), bonus);
                output.displayMessage("You added " + bonus + " to your " + ability + ".");
            }
        }
    }
}
