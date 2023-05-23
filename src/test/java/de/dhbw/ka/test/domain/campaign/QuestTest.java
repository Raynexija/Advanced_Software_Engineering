package de.dhbw.ka.test.domain.campaign;

import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.campaign.quests.Quest;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestTest {

    private static Quest testQuest;
    private static Character testCharacter;
    private static Encounter testEncounter;

    @BeforeAll
    public static void init() {
        testQuest = new Quest("TestQuest", "TestDescription");
        Creature testCreature = new Creature("Test", 15, 25, 0, 15, 11, 10, 1, 3, 2);
        testCharacter = CreateCharacter.named("Test Character")
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
        testEncounter = new Encounter("Test");
        testEncounter.addCreature(testCreature);
        testEncounter.addPlayerCharacter(testCharacter);
        testQuest.addEncounter(testEncounter);
    }

    @Test
    public void testQuestName() {
        assertEquals("TestQuest", testQuest.name());
    }

    @Test
    public void testQuestDescription() {
        assertEquals("TestDescription", testQuest.description());
    }

    @Test
    public void testQuestAssignedCharacters() {
        assertEquals(0, testQuest.assignedCharacters().size());
        testQuest.assignCharacter(testCharacter);
        assertEquals(1, testQuest.assignedCharacters().size());
        assertEquals(testCharacter, testQuest.assignedCharacters().get(0));
        testQuest.removeCharacter(testCharacter);
        assertEquals(0, testQuest.assignedCharacters().size());
    }

    @Test
    public void testQuestEncounters() {
        assertEquals(1, testQuest.encounters().size());
        assertEquals(testEncounter, testQuest.encounters().get(0));
        testQuest.removeEncounter(testEncounter);
        assertEquals(0, testQuest.encounters().size());
    }

    @Test
    public void testQuestCompleted() {
        assertFalse(testQuest.completed());
        testQuest = testQuest.markCompleted();
        assertTrue(testQuest.completed());
    }

    @Test
    public void testReward() {
        assertEquals(0, testQuest.rewards().size());
        testQuest.addReward("100 Gold");
        assertEquals(1, testQuest.rewards().size());
        assertEquals("100 Gold", testQuest.rewards().get(0));
        testQuest.addReward("75 Exp");
        assertEquals(2, testQuest.rewards().size());
        assertEquals("75 Exp", testQuest.rewards().get(1));
        testQuest.removeReward("100 Gold");
        assertEquals(1, testQuest.rewards().size());
        assertEquals("75 Exp", testQuest.rewards().get(0));
    }
}
