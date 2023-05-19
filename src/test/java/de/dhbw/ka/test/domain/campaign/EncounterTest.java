package de.dhbw.ka.test.domain.campaign;

import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import org.junit.jupiter.api.Test;

import static de.dhbw.ka.domain.character.AbilityScores.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testCreatureAbilityScore() {
        assertEquals(15, creature.abilityScore(STRENGTH).score());
        assertEquals(2, creature.abilityScore(STRENGTH).modifier());
    }

    @Test
    public void testCreatureAbilityScoreModifier() {
        int[] realModifiers = new int[]{-5, -4, -4, -3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10};
        for (int i = 0; i < 30; i++) {
            assertEquals(realModifiers[i], new Creature.CreatureAbilityScore(i + 1).modifier());
        }
    }

    @Test
    public void testCreatureTakeDamage() {
        assertEquals(15, creature.takeDamage(10));
        assertEquals(15, creature.hitPoints());
    }

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
}
