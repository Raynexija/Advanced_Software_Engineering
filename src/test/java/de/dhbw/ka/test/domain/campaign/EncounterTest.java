package de.dhbw.ka.test.domain.campaign;

import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EncounterTest {

    private final Creature creature = new Creature("Test", 15, 25, 0, 15, 11, 10, 1, 3, 2);
    private final Character character = CreateCharacter.named("Test Character")
            .ofRace(new Human())
            .isA(new Fighter())
            .withStrength(15)
            .withDexterity(14)
            .withConstitution(13)
            .withIntelligence(12)
            .withWisdom(10)
            .withCharisma(8)
            .maxHitPoints(10)
            .currentLevel(1)
            .build();

    @Test
    public void testGetParticipants() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        String expected = """
                Creatures:
                Test: 25
                Player Characters:
                Test Character: 10
                """;
        assertEquals(expected, encounter.getParticipants());
    }

    @Test
    public void testGetInitiative() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        encounter.addToInitiative(creature.name(), 10);
        encounter.addToInitiative(character.getName(), 15);
        String expected = """
                Name: Initiative
                Test Character: 15
                Test: 10
                """;
        assertEquals(expected, encounter.getInitiativeOrder().toString());
    }

    @Test
    public void testRemoveFromInitiative() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        encounter.addToInitiative(creature.name(), 10);
        encounter.addToInitiative(character.getName(), 15);
        encounter.removeFromInitiative(creature.name());
        String expected = """
                Name: Initiative
                Test Character: 15
                """;
        assertEquals(expected, encounter.getInitiativeOrder().toString());
    }

    @Test
    public void testRemoveCreature() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        encounter.addToInitiative(creature.name(), 10);
        encounter.addToInitiative(character.getName(), 15);
        encounter.removeFromInitiative(creature.name());
        encounter.removeCreature(creature);
        String expected = """
                Creatures:
                Player Characters:
                Test Character: 10
                """;
        assertEquals(expected, encounter.getParticipants());
    }

    @Test
    public void testRemovePlayerCharacter() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        encounter.addToInitiative(creature.name(), 10);
        encounter.addToInitiative(character.getName(), 15);
        encounter.removeFromInitiative(creature.name());
        encounter.removePlayerCharacter(character);
        String expected = """
                Creatures:
                Test: 25
                Player Characters:
                """;
        assertEquals(expected, encounter.getParticipants());
    }

    @Test
    public void testGetCreature() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        assertEquals(creature, encounter.getCreatures().get(0));
    }

    @Test
    public void testGetPlayerCharacter() {
        Encounter encounter = new Encounter();
        encounter.addCreature(creature);
        encounter.addPlayerCharacter(character);
        assertEquals(character, encounter.getPlayerCharacters().get(0));
    }
}
