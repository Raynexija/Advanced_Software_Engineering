package de.dhbw.ka.commands;

import de.dhbw.ka.Character;
import de.dhbw.ka.CreateCharacter;
import de.dhbw.ka.characterClasses.CharacterClass;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;
import de.dhbw.ka.races.Human;
import de.dhbw.ka.races.Race;

import java.util.List;

public class CreateCharacterCommand extends Command {
    private List<Character> characters;

    public CreateCharacterCommand(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public void execute(InputService input, OutputService output) {
        Character character = CreateCharacter
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
                .currentLevel(input.requestInt("Enter the current level of the character: "))
                .speaking(new String[]{"Celestial", "Elvish"})
                .equippedWith(new String[]{"Leather Armor", "Longsword", "Dagger"})
                .build();

        characters.add(character);
        output.displayMessage(character.toString());
    }

    private CharacterClass getCharacterClass(InputService input, OutputService output) {
        String characterClass = input.requestSelection("Chose a class:", new String[]{"Fighter", "Rogue", "Wizard", "Cleric", "Barbarian", "Bard", "Druid", "Monk", "Paladin", "Ranger", "Sorcerer", "Warlock"});

        switch (characterClass) {
            case "Fighter":
                return new de.dhbw.ka.characterClasses.Fighter();
        }
        return null;
    }

    private Race getRace(InputService input, OutputService output) {
        String race = input.requestSelection("Chose a race:", new String[]{"Human", "Elf", "Dwarf", "Halfling", "Gnome", "Half-Elf", "Half-Orc", "Tiefling"});

        switch (race) {
            case "Human":
                return new Human();
//            case "Elf":
//                return new Elf();
//            case "Dwarf":
//                return new Dwarf();
//            case "Halfling":
//                return new Halfling();
//            case "Gnome":
//                return new Gnome();
//            case "Half-Elf":
//                return new HalfElf();
//            case "Half-Orc":
//                return new HalfOrc();
//            case "Tiefling":
//                return new Tiefling();
            default:
                return null;
        }
    }
}
